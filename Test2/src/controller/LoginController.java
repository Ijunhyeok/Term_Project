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
		   
		  if(loginUser != null) {//로그인을 한 경우
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
		
		String msg = "로그인에 성공했습니다.";
		String url = "/main";
		
		switch(result) {
		case 1:
			HttpSession hs = request.getSession();
			hs.setAttribute("loginUser", id);
			break;
		case -1:
			msg = "아이디 또는 패스워드가 일치하지 않습니다.";
			url = "/login";
			break;
		default:
			msg = "데이터베이스 오류발생 관리자에게 문의해주세요.";
			url = "login";
			break;
		}
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		rd.forward(request, response);
	}

}
