package Entity;

import java.time.LocalDate;

import Control.Show_Schedule;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import DB.DB_Conn_Query;
import Control.Show_Schedule;

public class Schedule {
	
	public Schedule() {}
	
	public void schedule_sqlrun(int id) throws SQLException{	//스케줄 검색
		DB_Conn_Query db = new DB_Conn_Query();
		String sql = "SELECT 스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 FROM 스케줄 WHERE 유저_아이디 = "+id;
		ResultSet rs = db.executeQurey(sql);
		new Show_Schedule(rs);
	}
}