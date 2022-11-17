package Control;
import java.sql.Date;

import Entity.Schedule;

public class Show_Schedule {
	private String name;
	private String yoil;
	private int startTime;
	private int endTime;
	private String fix;
	private Date date;
	private String memo;
	
	public Show_Schedule() { Get_Index(); }
	
	public Show_Schedule(String name, String yoil, int startTime, 
			int endTime, String fix, Date date , String memo){
		this.name = name;
		this.yoil = yoil;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fix = fix;
		this.date = date;
		this.memo = memo;
		
		Get_Index();
	}
	//index를 반환하는 함수 추가
	public int[] Get_Index() {
		int s_row_index,e_row_index, col_index=0;	//[시간][요일], 시간 -> 시작시간 ~ 종료시간
		String week[] = {"월", "화", "수", "목", "금", "토", "일"};
		
		//col_index(요일) 구하기
		for(int i=0;i<6;i++) {	
			if(yoil.equals(week[i])) {
				col_index=i;
				break;
			}
		}
		//s_row_index(시작시간) 구하기
		s_row_index=startTime-9; 	//9시부터 표시하기 때문
		
		//e_row_index(종료시간) 구하기
		e_row_index=endTime-9-1;	//1 작게 인덱스 줘야됨
		
		int result[]= {s_row_index, e_row_index, col_index};
		
		return result;
	}
	
	//그리고? name, 메모 : ui에 가져와야할 정보
	//그리고? 고정 : 나중에 생각해보자...
}
