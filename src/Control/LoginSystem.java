package Control;

import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DB_Conn_Query;

public class LoginSystem {

	public  LoginSystem(){ }
	public String LoginSystem(String id, String pw) {
		DB_Conn_Query db = new DB_Conn_Query();
		String sql = "SELECT 유저_아이디, 유저_비밀번호, 관리자_여부 FROM 유저";
		ResultSet src = db.executeQurey(sql);
		Boolean success = false;
		String m = "일반";
		try {
			while(src.next()) {
				if(id.equals(src.getString("유저_아이디"))&&pw.equals(src.getString("유저_비밀번호"))) {
					//유저테이블 DB의 아이디, 비밀번호가 일치할 경우
					success = true; //로그인 성공
					if(src.getString("관리자_여부").equals("1")) {	//관리자인 경우
						m="관리";
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(success)return m;
		else return null;
	}
}
