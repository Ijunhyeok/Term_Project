package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
	public static void main(String[] args) {
		try {
			Conn.getCon();
//			System.out.println("연결성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//연결용
	public static Connection getCon() throws Exception {
		final String DBURL = "jdbc:mysql://localhost:3306/mento?serverTimezone=UTC";
		final String DBID = "root";
		final String DB_PASSWORD = "yhoug4536";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DBURL,DBID,DB_PASSWORD);
		return con;
	} 
	
	//자원 회수용
	//connecton -> DB(데이터 베이스) 연결용
	//PreparedStatement -> db쿼리 진행용
	//ResultSet -> 쿼리결과 있다면 반환하는용
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
