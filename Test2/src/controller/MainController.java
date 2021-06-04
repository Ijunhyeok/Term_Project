package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.BoardDTO;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   List<BoardDTO> list = BoardDAO.selecttBoardList();
	   
	   request.setAttribute("list", list);
	   
	   for(BoardDTO dto : list){
//		   System.out.println("dto : "+dto);
	   }

      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
      rd.forward(request, response);
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }

}