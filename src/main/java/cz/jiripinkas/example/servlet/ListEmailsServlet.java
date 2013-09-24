package cz.jiripinkas.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.jiripinkas.example.entity.Email;
import cz.jiripinkas.example.service.DatabaseService;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list-emails.html")
public class ListEmailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Email> list = DatabaseService.listEmails();
		request.setAttribute("emails", list);
		request.getRequestDispatcher("/WEB-INF/jsp/emails.jsp").forward(request, response);
	}

}
