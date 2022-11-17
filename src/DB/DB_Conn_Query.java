package DB;
//DB 연결 코드
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import oracle.jdbc.OracleTypes;

public class DB_Conn_Query {
   Connection con = null;
   String url = "jdbc:oracle:thin:@localhost:1521:XE";
   String id = "suser";      String password = "1234";
   public DB_Conn_Query() {
     try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("드라이버 적재 성공");
     } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
   }
   public void DB_Connect() {
     try {
         con = DriverManager.getConnection(url, id, password);
         System.out.println("DB 연결 성공");
     } catch (SQLException e) {         System.out.println("Connection Fail");      }
   }
   public void schedule_sqlrun(int id) throws SQLException{	//스케줄 검색
	    String query = "SELECT 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 FROM 스케줄 WHERE 유저_아이디 = "+id;
	    try { DB_Connect();
	    	  Statement stmt = con.createStatement();
	          ResultSet rs = stmt.executeQuery(query);
	          while (rs.next()) {
	              System.out.print("\t" + rs.getString(1));
	              System.out.print("\t" + rs.getInt(2));
	              System.out.print("\t" + rs.getInt(3));
	              System.out.print("\t" + rs.getString(4));
	              System.out.print("\t" + rs.getDate(5));
	              System.out.print("\t" + rs.getString(6) + "\n");
	           }
	          
	          stmt.close();    rs.close();
	    } catch (SQLException e) { e.printStackTrace(); 
	    } finally {   con.close(); }
	    
	   }
}
