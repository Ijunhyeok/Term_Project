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
//			System.out.println("���Ἲ��");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�����
	public static Connection getCon() throws Exception {
		final String DBURL = "jdbc:mysql://localhost:3306/mento?serverTimezone=UTC";
		final String DBID = "root";
		final String DB_PASSWORD = "yhoug4536";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DBURL,DBID,DB_PASSWORD);
		return con;
	} 
	
	//�ڿ� ȸ����
	//connecton -> DB(������ ���̽�) �����
	//PreparedStatement -> db���� �����
	//ResultSet -> ������� �ִٸ� ��ȯ�ϴ¿�
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
