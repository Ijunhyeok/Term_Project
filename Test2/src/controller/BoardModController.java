package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.BoardDTO;

/**
 * Servlet implementation class BoardModController
 */
@WebServlet("/board/mod")
public class BoardModController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seq = request.getParameter("seq");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
//		System.out.println("seq : "+seq);
//		System.out.println("title : "+title);
//		System.out.println("content : "+content);
//		System.out.println("wirter : "+writer);
		
		int intseq = Integer.parseInt(seq);
		
		BoardDTO param = new BoardDTO();
		param.setSeq(intseq);
		param.setContent(content);
		param.setTitle(title);
		param.setWriter(writer);
		
		int result = BoardDAO.updateBoard(param);
		
		String msg = "";
		switch(result) {
		case 1 :
			msg = "수정에 성공했습니다.";
			break;
		case 0 :
			msg = "알수없는 에러가 발생했습니다.";
			break;
		case -1 :
			msg = "수정에 실패했습니다.";
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", "/main");
		
		rd.forward(request, response);
	}

}
