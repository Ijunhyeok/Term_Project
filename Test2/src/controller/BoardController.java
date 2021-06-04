package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import model.BoardDTO;

@WebServlet("/board")
public class BoardController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	  HttpSession hs = request.getSession();
	  String loginUser = (String) hs.getAttribute("loginUser");
	   
	  if(loginUser == null) {//로그인을 하지 않은 경우
		  response.sendRedirect("/login");
		  return;
	  }
	  
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board.jsp");
      rd.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title = request.getParameter("title");
      String writer = request.getParameter("writer");
      String content = request.getParameter("content");
      
//      System.out.println("title : "+title);
//      System.out.println("writer : "+writer);
//      System.out.println("content : "+content);
      
      BoardDTO boardDto = new BoardDTO();
      boardDto.setTitle(title);
      boardDto.setWriter(writer);
      boardDto.setContent(content);
            
      int result = BoardDAO.insertBoard(boardDto);
      
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
      request.setAttribute("msg", "글 작성에 성공했습니다.");
      request.setAttribute("url", "/main");
      
      
      if(result !=1) {
    	  request.setAttribute("msg", "글 작성에 성공했습니다.");
          request.setAttribute("url", "/main");
      }
      rd.forward(request, response);
      
   }

}