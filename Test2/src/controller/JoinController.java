package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.userDAO;
import model.UserDTO;


@WebServlet("/join")
public class JoinController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�α��� ����� reredirect
		HttpSession hs = request.getSession();
		  String loginUser = (String) hs.getAttribute("loginUser");
		   
		  if(loginUser != null) {//�α����� �� ���
			  response.sendRedirect("/main");
			  return;
		  }
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/join.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ �ޱ�
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
//		System.out.println("id :: "+id);
//		System.out.println("pw :: "+pw);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserID(id);
		userDTO.setUserPW(pw);
		
		//������ ���̽��� �ִ´�.
		int result = userDAO.doJoin(userDTO);
		
		String msg = "���Կ� �����߽��ϴ�.";
		String url = "/login";
		if(result != 1) {
			msg = "���Կ� �����߽��ϴ�.";
			url = "/join";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		rd.forward(request, response);
	}

}
