package controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/del")
public class BoardDelController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seq = request.getParameter("seq");
		//System.out.println("seq : "+seq);
		
		BoardDTO param = new BoardDTO();
		param.setSeq(Integer.parseInt(seq));
		
		int result = BoardDAO.deleteBoard(param);
//		System.out.println("result : "+result);
		//성공하면 1, 실패하면 -1 or 0
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		String msg = "삭제에 성공했습니다.";
		String url = "/main";
		if(result != 1) {//실패
			msg  = "삭제에 실패했습니다.";
		}
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
