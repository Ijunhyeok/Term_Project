package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conection.Conn;
import model.BoardDTO;

public class BoardDAO {
	
	//조회수 증가
	public static int updateViewCnt(BoardDTO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE board_tb SET view_cnt = view_cnt + 1 WHERE seq = ?";
		
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getSeq());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	//게시물 삭제
	public static int deleteBoard(BoardDTO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM board_tb WHERE SEQ = ?";
		
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getSeq());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		} finally {
			Conn.close(con, ps, null);
		}
		
		
		return result;
	}
	
	//게시물 수정
	public static int updateBoard(BoardDTO param) {//성공1, 실패 -1, 알수없는 에러0
		int result = 0;
		
		Connection con = null;//db연결용
	    PreparedStatement ps = null;//쿼리 진행용
	    String sql = "UPDATE board_tb SET title = ?,writer = ?, content = ? WHERE SEQ = ?";
	    
	    try {
	    	con = Conn.getCon();
	    	ps = con.prepareStatement(sql);
	    	ps.setString(1, param.getTitle());
	    	ps.setString(2, param.getWriter());
	    	ps.setString(3, param.getContent());
	    	ps.setInt(4, param.getSeq());
	    	
	    	result = ps.executeUpdate();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	result = -1;
	    } finally {
	    	Conn.close(con, ps, null);
	    }
		
		return result;
	}
	
	//게시물 하나 가져오기
	public static BoardDTO selectBoardOne(BoardDTO param) {
		BoardDTO boardDto = new BoardDTO();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board_tb WHERE SEQ = ?";
		
		try {
			con = Conn.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getSeq());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				boardDto.setSeq(rs.getInt("seq"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setReg_dt(rs.getString("Reg_dt"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setViewCnt(rs.getInt("view_cnt"));
			}
			
		} catch (Exception e) {
	          e.printStackTrace();
	      } finally {
	    	  Conn.close(con, ps, null);
	      }
		
		return boardDto;
	}
	
	//게시글 가져오기
	public static List<BoardDTO> selecttBoardList(){
		List<BoardDTO> list = new ArrayList<BoardDTO>(); //그릇
		
		//DB에서 가져오기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board_tb ORDER BY SEQ DESC";
		
		try {
			con = Conn.getCon();
			ps = con.prepareCall(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setReg_dt(rs.getString("reg_dt"));
				boardDTO.setWriter(rs.getString("writer"));
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setViewCnt(rs.getInt("view_cnt"));
				
				list.add(boardDTO);
			}
			
		} catch (Exception e) {
	          e.printStackTrace();
	       } finally {
	    	   Conn.close(con, ps, null);
	       }
	       
	       return list;
	}
   
   
   //게시글 작성
   public static int insertBoard(BoardDTO param) {//성공1, 실패 -1, 알수없는 에러0
	   int result = 0;
	   
      Connection con = null;//db연결용
      PreparedStatement ps = null; //쿼리진행용
      //ResultSet rs = null; //조회용
      
      String sql = "INSERT INTO board_tb(title, content, writer, reg_dt) VALUES(?, ?, ?, now())";
      
      try {
         con = Conn.getCon();
         ps = con.prepareStatement(sql);
         ps.setString(1, param.getTitle());
         ps.setString(2, param.getContent());
         ps.setString(3, param.getWriter());
         
        result = ps.executeUpdate();
         
      } catch (Exception e) {
    	  result = -1;
         e.printStackTrace();
      } finally {
    	  Conn.close(con, ps, null);
      }
      
      return result;
   
   }
}