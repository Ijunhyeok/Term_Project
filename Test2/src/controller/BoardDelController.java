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
		//�����ϸ� 1, �����ϸ� -1 or 0
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		String msg = "������ �����߽��ϴ�.";
		String url = "/main";
		if(result != 1) {//����
			msg  = "������ �����߽��ϴ�.";
		}
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
