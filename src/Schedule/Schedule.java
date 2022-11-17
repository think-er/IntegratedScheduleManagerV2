package Schedule;

import java.time.LocalDate;
import java.sql.Date;
import java.time.DayOfWeek;

public class Schedule {
	private String name;
	private String yoil;
	private int startTime;
	private int endTime;
	private String fix;
	private Date date;
	private String memo;
	
	
	
	public Schedule(String name, String yoil, int startTime, 
			int endTime, String fix, Date date , String memo) {
		this.name = name;
		this.yoil = yoil;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fix = fix;
		this.date = date;
		this.memo = memo;
	}
}
