package com.wx;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("path");
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		StringBuffer redirect_url = new StringBuffer();
		redirect_url.append(scheme).append("://").append(serverName).append(path);
		System.err.println(redirect_url.toString());
		try {
			String auth_url = String.format(Common.AUTHORIZE_SNSAPI_USERINFO_URL, Common.APPID, URLEncoder.encode(redirect_url.toString(), "UTF-8"));
			response.sendRedirect(auth_url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
