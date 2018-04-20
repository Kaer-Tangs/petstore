package com.newer.petstore.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
//		session.setAttribute(name, value);
//		session.getAttribute(name)
//		session.removeAttribute(name);
//		session.getCreationTime()
//		session.getLastAccessedTime()
//		session.getMaxInactiveInterval()
		
		// 会话失效（注销）
		session.invalidate();
		
	}

}
