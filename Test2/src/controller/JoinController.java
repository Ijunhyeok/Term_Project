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
		
		//로그인 사용자 reredirect
		HttpSession hs = request.getSession();
		  String loginUser = (String) hs.getAttribute("loginUser");
		   
		  if(loginUser != null) {//로그인을 한 경우
			  response.sendRedirect("/main");
			  return;
		  }
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/join.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
//		System.out.println("id :: "+id);
//		System.out.println("pw :: "+pw);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserID(id);
		userDTO.setUserPW(pw);
		
		//데이터 베이스에 넣는다.
		int result = userDAO.doJoin(userDTO);
		
		String msg = "가입에 성공했습니다.";
		String url = "/login";
		if(result != 1) {
			msg = "가입에 실패했습니다.";
			url = "/join";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		rd.forward(request, response);
	}

}
