package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.PreparableStatement;

import conection.Conn;
import model.UserDTO;

public class userDAO {
	
	//�α��� �޼���
	public static int dologin(UserDTO param) {//����1, ����-1, �˼����� ����0, ����-2
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user_tb WHERE user_id = ? AND user_pw = PASSWORD(?)";
		
		try {
			con = Conn.getCon(); //�����ͺ��̽� ����
			ps = con.prepareStatement(sql); //��������
			ps.setString(1, param.getUserID());
			ps.setString(2, param.getUserPW());
			rs = ps.executeQuery(); //���� ������ ��ȯ
			
			if(rs.next()) {
				result = 1; //�α��� ����
			}else {
				result = -1; //�α��� ����(���̵� �Ǵ� ��й�ȣ�� ����ġ)
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, rs);
		}
		
		return result;
	}
	
	//ȸ������ �޼���
	public static int doJoin(UserDTO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null; //��ȯ��
		
		String sql = "INSERT INTO user_tb(USER_ID, USER_PW) VALUES(?,PASSWORD(?))";
		
		try {
			con = Conn.getCon(); //������ ���̽� ���� ����
			ps = con.prepareStatement(sql); //���� ����
			ps.setString(1, param.getUserID());
			ps.setString(2, param.getUserPW());
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = -1;
		} finally {
			Conn.close(con, ps, null);
		}
		return result;
	}

	private static String getUserID() {
		// TODO Auto-generated method stub
		return null;
	}

}
