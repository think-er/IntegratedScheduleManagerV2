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
   String id = "hmart";      String password = "1234";
   public DB_Conn_Query() {
     try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("드라이버 적재 성공");
     } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
   }
   private void DB_Connect() {
     try {
         con = DriverManager.getConnection(url, id, password);
         System.out.println("DB 연결 성공");
     } catch (SQLException e) {         System.out.println("Connection Fail");      }
   }
}
