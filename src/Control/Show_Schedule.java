package Control;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Schedule;

public class Show_Schedule {
	private String name;
	private String yoil;
	private int startTime;
	private int endTime;
	private String fix;
	private Date date;
	private String memo;
	
	public Show_Schedule(ResultSet rs) {
		try {
			while (rs.next()) 
			{
				this.name = rs.getString(1);
				this.yoil = rs.getString(2);
				this.startTime = rs.getInt(3);
				this.endTime = rs.getInt(4);
				this.fix = rs.getString(5);
				this.date = rs.getDate(6);
				this.memo = rs.getString(7);
				Get_Index();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		System.out.print(s_row_index+ " ");
		System.out.print(e_row_index+" ");
		System.out.print(col_index+"\n");
		
		int result[]= {s_row_index, e_row_index, col_index};
		
		return result;
	}
	
	//그리고? name, 메모 : ui에 가져와야할 정보
	//그리고? 고정 : 나중에 생각해보자...
}
