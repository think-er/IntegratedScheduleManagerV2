package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import DB.DB_Conn_Query;
//개인
//-> 로그인한 id의 스케줄, 로그인한 id가 소속된 팀의 통합스케줄
//통합
//-> 로그인한 id가 소속된 팀의 모든 팀원의 스케줄,  로그인한 id가 소속된 팀의 통합스케줄
public class duplicatedCheck {
	DB_Conn_Query db = new DB_Conn_Query();
	String ID, WEEK, FIX;
	int START, END;
	Date DATE;
	public void getData(String id, Date date,String week, String fix, int start, int end) {
		ID=id;
		DATE=date;
		WEEK=week;
		FIX=fix;
		START=start;
		END=end;
	}
	public boolean duplicatedCheck() {
		boolean success=true;
		boolean s=true;
		
		success = timeCheck("1");	//고정스케줄과 시간 같은지 체크
		
		//비고정 : 날짜 중복 check -> 시간 중복 check
		//비고정 일정들 : 날짜가 다르면 시간이 같아도 됨
		if(FIX=="0") {
			String sql = "SELECT 날짜 "
					+ "FROM 스케줄 "
					+ "WHERE 유저_아이디 = " + ID + "  "
					+ "고정여부 = '0' "
					+ "UNION "
					+ "SELECT 날짜 "
					+ "FROM 통합스케줄, 소속, 팀 "
					+ "WHERE 소속.유저_아이디 = " + ID + " AND "
					+ "팀.팀_번호 = 소속.팀_번호 AND "
					+ "통합스케줄.팀_번호 = 소속.팀_번호"
					+ "고정여부 = '0'";
			ResultSet src = db.executeQuery(sql);
			
			try {
				while(src.next()) {
					Date d = src.getDate(1);
					//날짜가 같을 시 비고정 스케줄과 시간 비교
					if(DATE == d)
						s = timeCheck("0");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(success&&s)return true;
		else return false;
	}
	public boolean timeCheck(String fix) {
		String sql = "SELECT 시작시간, 종료시간  "
				+ "FROM 스케줄 "
				+ "WHERE 유저_아이디 = " + ID + " AND "
				+ "요일 = '" + WEEK + "' AND "
				+ "고정여부 = '" + fix + "' "
				+ "UNION "
				+ "SELECT 시작시간, 종료시간  "
				+ "FROM 통합스케줄, 소속, 팀 "
				+ "WHERE 소속.유저_아이디 = " + ID + " AND "
				+ "팀.팀_번호 = 소속.팀_번호 AND "
				+ "통합스케줄.팀_번호 = 소속.팀_번호 AND "
				+ "요일 = '" + WEEK + "' AND "
				+ "고정여부 = '" + fix + "'";
		ResultSet src = db.executeQuery(sql);
		
		int stTime, edTime;
		
		try {
			while(src.next()) {
				stTime = src.getInt(1);
				edTime = src.getInt(2);
				
				if(edTime > START && stTime < END) {
					return false;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}
}
