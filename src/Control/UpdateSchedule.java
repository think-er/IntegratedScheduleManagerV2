package Control;

import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DB_Conn_Query;
import UI.HomeUI;

public class UpdateSchedule {
	DB_Conn_Query db = new DB_Conn_Query();
	public UpdateSchedule() {
		for(int i=0; i<14; i++) {
			for(int j=0; j<7; j++) {
				HomeUI.event[i][j].setEventMode(0);
				// 이전에 출력했던 이벤트들을 전부 끈다.
				HomeUI.event[i][j].viewEventMode();
				// 새로운 모드로 이벤트들을 조회한다.
			
			}
		}
		
		// 데이터베이스에서 개인 시간표 가져오기
		try {
			String sql = "SELECT 스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 FROM 스케줄 WHERE (유저_아이디="+HomeUI.viewUser +
					"AND 날짜 BETWEEN TO_DATE('" + HomeUI.StartOfWeekFormat + "', 'YYYY-MM-DD') " +
					"AND TO_DATE('" + HomeUI.EndOfWeekFormat + "', 'YYYY-MM-DD'))" +
					"OR (유저_아이디="+HomeUI.viewUser +
					"AND 고정여부='1')";

			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString(1);
				// 요일 문자가 아닌 숫자로 받기
				String days = rs.getString(2);	
				
				int days2 = 0;
				
				if (days.equals("일"))
					days2 = 0;
				else if (days.equals("월"))
					days2 = 1;
				else if (days.equals("화"))
					days2 = 2;
				else if (days.equals("수"))
					days2 = 3;
				else if (days.equals("목"))
					days2 = 4;
				else if (days.equals("금"))
					days2 = 5;
				else if (days.equals("토"))
					days2 = 6;
				int startTime = rs.getInt(3);
				int endTime = rs.getInt(4);
				
				for(int i=startTime - 9; i < endTime - 9; i++) {
					HomeUI.event[i][days2].setEventName(name);
					HomeUI.event[i][days2].setEventMode(1);
					HomeUI.event[i][days2].viewEventMode();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		};
		
		try {
			
			String sql = "SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 ="+HomeUI.viewUser;
			ResultSet rs = db.executeQuery(sql);
			String _teamNum = "";
			while(rs.next()) {
				_teamNum = rs.getString(1);
			}
			
			sql = "SELECT 통합스케줄_이름, 요일, 시작시간, 종료시간 FROM 통합스케줄 WHERE (팀_번호="+_teamNum +
					"AND 날짜 BETWEEN TO_DATE('" + HomeUI.StartOfWeekFormat + "', 'YYYY-MM-DD') "+ 
					"AND TO_DATE('" + HomeUI.EndOfWeekFormat + "', 'YYYY-MM-DD'))" +
					"OR (팀_번호=" + _teamNum +
					"AND 고정여부='1')";

			rs = db.executeQuery(sql);
			while(rs.next()) {
				String _teamName = rs.getString(1);
				// 요일 문자가 아닌 숫자로 받기
//				String _date = rs.getString(2);
//				String _dateFormat[] = _date.split("-");		
				// 날짜를 읽어서 변환하는 것을 만들었지만 요일을 읽으면 되므로 필요없음.
//				int year = Integer.parseInt(_dateFormat[0]);
//				int month = Integer.parseInt(_dateFormat[1]);
//				int day = Integer.parseInt(_dateFormat[2].substring(0, 2));
//				
//				LocalDate date = LocalDate.of(year, month, day);
//				DayOfWeek dayOfWeek = date.getDayOfWeek();
//				
//				int yoil = dayOfWeek.getValue();
//				
				String days = rs.getString(2);	
				
				int days2 = 0;
				
				if (days.equals("일"))
					days2 = 0;
				else if (days.equals("월"))
					days2 = 1;
				else if (days.equals("화"))
					days2 = 2;
				else if (days.equals("수"))
					days2 = 3;
				else if (days.equals("목"))
					days2 = 4;
				else if (days.equals("금"))
					days2 = 5;
				else if (days.equals("토"))
					days2 = 6;
				int startTime = rs.getInt(3);
				int endTime = rs.getInt(4);
//				String memo = rs.getString(6);
				
				for(int i=startTime - 9; i < endTime - 9; i++) {
					HomeUI.event[i][days2].setEventName(_teamName);
					HomeUI.event[i][days2].setEventMode(2);
					HomeUI.event[i][days2].viewEventMode();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		};
	}
	
}
