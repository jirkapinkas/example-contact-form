package cz.jiripinkas.example.servlet;

import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.jiripinkas.example.entity.Email;
import cz.jiripinkas.example.service.DatabaseService;
import cz.jiripinkas.example.service.SendEmailService;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/send-email.html")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");

		try {
			SendEmailService.sendEmail(from, to, subject, body);
			Email email = new Email(from, to, subject, body);
			DatabaseService.insert(email);
		} catch (AuthenticationFailedException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		} catch (MessagingException e) {
			response.sendRedirect("index.jsp?error=true");
			return;
		}
		response.sendRedirect("index.jsp?sent=true");
	}

}
