package Schedule;

import java.time.LocalDate;
import java.time.DayOfWeek;

public class Schedule {
	private String id;
	private String name;
	private LocalDate startTime;
	private LocalDate endTime;
	private boolean fix;
	private String memo;
	
	public Schedule(String id, String name, LocalDate startTime, 
			LocalDate endTime, boolean fix, String memo) {
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fix = fix;
		this.memo = memo;
	}
}
