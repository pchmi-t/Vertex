package com.fmi.javaee.vertex.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.inject.Singleton;

@Singleton
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String INDEX_PAGE_URL = "/vertex/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		resp.sendRedirect(INDEX_PAGE_URL);
	}

}
