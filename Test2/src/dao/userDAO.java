package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.PreparableStatement;

import conection.Conn;
import model.UserDTO;

public class userDAO {
	
	//로그인 메서드
	public static int dologin(UserDTO param) {//성공1, 실패-1, 알수없는 오류0, 오류-2
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user_tb WHERE user_id = ? AND user_pw = PASSWORD(?)";
		
		try {
			con = Conn.getCon(); //데이터베이스 연결
			ps = con.prepareStatement(sql); //쿼리연결
			ps.setString(1, param.getUserID());
			ps.setString(2, param.getUserPW());
			rs = ps.executeQuery(); //쿼리 실행결과 반환
			
			if(rs.next()) {
				result = 1; //로그인 성공
			}else {
				result = -1; //로그인 실패(아이디 또는 비밀번호가 불일치)
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Conn.close(con, ps, rs);
		}
		
		return result;
	}
	
	//회원가입 메서드
	public static int doJoin(UserDTO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null; //반환용
		
		String sql = "INSERT INTO user_tb(USER_ID, USER_PW) VALUES(?,PASSWORD(?))";
		
		try {
			con = Conn.getCon(); //데이터 베이스 연결 성공
			ps = con.prepareStatement(sql); //쿼리 매핑
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
