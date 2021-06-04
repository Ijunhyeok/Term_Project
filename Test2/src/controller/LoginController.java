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


@WebServlet("/login")
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		  String loginUser = (String) hs.getAttribute("loginUser");
		   
		  if(loginUser != null) {//�α����� �� ���
			  response.sendRedirect("/main");
			  return;
		  }
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		
		UserDTO param = new UserDTO();
		param.setUserID(id);
		param.setUserPW(pw);
		
		int result = userDAO.dologin(param);
		
		String msg = "�α��ο� �����߽��ϴ�.";
		String url = "/main";
		
		switch(result) {
		case 1:
			HttpSession hs = request.getSession();
			hs.setAttribute("loginUser", id);
			break;
		case -1:
			msg = "���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�.";
			url = "/login";
			break;
		default:
			msg = "�����ͺ��̽� �����߻� �����ڿ��� �������ּ���.";
			url = "login";
			break;
		}
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		rd.forward(request, response);
	}

}
