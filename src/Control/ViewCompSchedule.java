package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DB_Conn_Query;
import UI.HomeUI;

public class ViewCompSchedule {
	DB_Conn_Query db = new DB_Conn_Query();
	public ViewCompSchedule() {
		
		for(int i=0; i<14; i++) {
			for(int j=0; j<7; j++) {
				HomeUI.event[i][j].setEventCompViewMode(false);
				HomeUI.event[i][j].viewEventCompMode();
			}
		}
		
		try {
			String sql = "SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 ="+HomeUI.viewUser;
			ResultSet rs = db.executeQuery(sql);
			
			String _teamNum = "";
			ArrayList<String> _teamUser = new ArrayList<String>();
			
			while(rs.next()) {
				_teamNum = rs.getString(1);
			}
			
			sql = "SELECT 유저_아이디 FROM 소속 WHERE 팀_번호 = "+_teamNum;
			rs = db.executeQuery(sql);
			
			while(rs.next()) {
				_teamUser.add(rs.getString(1));
			}
			
			for(int k=0; k<_teamUser.size(); k++) {
				sql = "SELECT 요일, 시작시간, 종료시간 FROM 스케줄 WHERE 유저_아이디="+_teamUser.get(k) +
						"AND 날짜 BETWEEN TO_DATE('" + HomeUI.StartOfWeekFormat + "', 'YYYY-MM-DD') "+ 
						"AND TO_DATE('" + HomeUI.EndOfWeekFormat + "', 'YYYY-MM-DD')" +
						"OR (유저_아이디="+_teamUser.get(k) +
						"AND 고정여부='1')";
				
				rs = db.executeQuery(sql);
				while(rs.next()) {
					String days = rs.getString(1);
					int startTime = rs.getInt(2);
					int endTime = rs.getInt(3);
					
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
					
					for(int i=startTime - 9; i < endTime - 9; i++) {
						HomeUI.event[i][days2].setEventCompViewMode(HomeUI.viewCompEventMode);
						HomeUI.event[i][days2].viewEventCompMode();
					}
				}
			}
		} catch(SQLException s) {
			s.printStackTrace();
		}
	}
}
