package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DB.DB_Conn_Query;

public class duplicatedCheck {
	DB_Conn_Query db = new DB_Conn_Query();
	String ID, WEEK, FIX;
	int START, END;
	String DATE;
	public void getData(String id, Date date,String week, String fix, int start, int end) {
		ID=id;
		WEEK=week;
		FIX=fix;
		START=start;
		END=end;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DATE = simpleDateFormat.format(date);
	}
	//개인 중복 : PersonalDC()
	//-> 로그인한 id의 스케줄, 로그인한 id가 소속된 팀의 통합스케줄
	public boolean PersonalDC() {
		boolean success=true;
		boolean s=true;
		
		success = timeCheck(ID, "1");	//고정스케줄과 시간 같은지 체크
		
		//비고정 : 날짜 중복 check -> 시간 중복 check
		//비고정 일정들 : 날짜가 다르면 시간이 같아도 됨
		s = dateCheck(ID);
		
		if(success&&s)return true;
		else return false;
	}
	//통합 중복 : IntegrationDC()
	//-> 로그인한 id가 소속된 팀의 모든 팀원의 스케줄,  로그인한 id가 소속된 팀의 통합스케줄
	public boolean IntegrationDC() {
		boolean success=true;
		boolean s=true;
		
		int n=0;
		String sql2 = "SELECT COUNT(*) "
				+ "FROM 소속 "
				+ "WHERE 팀_번호 = (SELECT 팀_번호 "
				+ "FROM 소속 "
				+ "WHERE 유저_아이디 = " + ID + ")";
		ResultSet src2 = db.executeQuery(sql2);
		try {
			while(src2.next()) {
				n =src2.getInt(1);	//팀원수 가져오기
			}
		} catch (SQLException e1) {
			//e1.printStackTrace();
		}
		
		String []id = new String[n];	//id 배열
		int i=0;
		
		String sql = "SELECT 유저_아이디 "
				+ "FROM 소속 "
				+ "WHERE 팀_번호 = (SELECT 팀_번호 "
				+ "FROM 소속 "
				+ "WHERE 유저_아이디 = " + ID + ")";
		
		ResultSet src = db.executeQuery(sql);
		
		try {
			while(src.next()) {
				id[i++] = Integer.toString(src.getInt(1));
			}
		} catch (SQLException e1) {
			//e1.printStackTrace();
		}
		
		for(i=0;i<n;i++) {
			success = timeCheck(id[i], "1");	//고정스케줄과 시간 같은지 체크
			
			//비고정 : 날짜 중복 check -> 시간 중복 check
			//비고정 일정들 : 날짜가 다르면 시간이 같아도 됨
			s = dateCheck(id[i]);
		}
		
		if(success&&s)return true;
		else return false;
	}
	
	public boolean timeCheck(String id, String fix) {
		String sql = "SELECT 시작시간, 종료시간  "
				+ "FROM 스케줄 "
				+ "WHERE 유저_아이디 = " + id + " AND "
				+ "요일 = '" + WEEK + "' AND "
				+ "고정여부 = '" + fix + "' "
				+ "UNION "
				+ "SELECT 시작시간, 종료시간  "
				+ "FROM 통합스케줄, 소속, 팀 "
				+ "WHERE 소속.유저_아이디 = " + id + " AND "
				+ "팀.팀_번호 = 소속.팀_번호 AND "
				+ "통합스케줄.팀_번호 = 소속.팀_번호 AND "
				+ "요일 = '" + WEEK + "' AND "
				+ "고정여부 = '" + fix + "'";
		ResultSet src = db.executeQuery(sql);
		
		int stTime, edTime;
		boolean s=true;
		try {
			while(src.next()) {
				stTime = src.getInt(1);
				edTime = src.getInt(2);
				
				if(edTime > START && stTime < END) {
					s = false;
				}
			}
		} catch (SQLException e1) {
			//e1.printStackTrace();
		}
		return s;
	}
	
	public boolean dateCheck(String id) {
		boolean s=true;
		String sql = "SELECT 날짜 "
				+ "FROM 스케줄 "
				+ "WHERE 유저_아이디 = " + id + " AND "
				+ "고정여부 = '0' "
				+ "UNION "
				+ "SELECT 날짜 "
				+ "FROM 통합스케줄, 소속, 팀 "
				+ "WHERE 소속.유저_아이디 = " + id + " AND "
				+ "팀.팀_번호 = 소속.팀_번호 AND "
				+ "통합스케줄.팀_번호 = 소속.팀_번호 AND "
				+ "고정여부 = '0'";
		System.out.println(sql);
		ResultSet src = db.executeQuery(sql);
		
		try {
			while(src.next()) {
				String d = src.getDate(1).toString();
				//날짜가 같을 시 비고정 스케줄과 시간 비교
				System.out.println(DATE + " " + d);
				if(DATE.equals(d)) {
					if(!timeCheck(id, "0")) 
						s=false;
					System.out.println(s);
				}
				//날짜가 다를 시 고정 스케줄과 시간 비교
				else {
					if(!timeCheck(id, "1")) 
						s=false;
				}
			}
			
		} catch (SQLException e1) {
			//e1.printStackTrace();
		}
		return s;
	}
}
