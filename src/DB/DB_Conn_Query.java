package DB;
//DB 연결 코드
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

//import Entity.Schedule;
import oracle.jdbc.OracleTypes;

public class DB_Conn_Query {
   public Connection con = null;
   public Statement stmt = null;
   String url = "jdbc:oracle:thin:@localhost:1521:XE";
   String id = "suser";      String password = "1234";
   
   public DB_Conn_Query() {
     try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("드라이버 적재 성공");
     } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
     
     try {
         con = DriverManager.getConnection(url, id, password);
         System.out.println("DB 연결 성공");
         stmt = con.createStatement();
     } catch (SQLException e) {         System.out.println("Connection Fail");      }
   }
   
   public ResultSet executeQuery(String sql) {
		//SQL문 실행하기 위한 메소드 - Parameter : String객체로 만든 SQL문
		//실행결과는 ResultSet으로 반환
		
		ResultSet src = null;
		try {
			src = stmt.executeQuery(sql);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("SQL 실행 에러");
			return null;
		}
		
		return src;
	}
	
	public int executeUpdate(String sql) {
		int n = 0;
		try {
			n = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("SQL 실행 에러");
			return -1;
		}
		return n;
	}
}
