package com.cpi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		checkCookie(request, response);
	}
	
	private void checkCookie(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Cookie cookie = null;
		Cookie[] cookieArray = request.getCookies();
		String customCookie = null;
		PrintWriter out = response.getWriter();
		System.out.println("doGet");
		if(cookieArray != null){
			for(int i = 0 ; i < cookieArray.length; i++){
				out.print("Cookies in Array: "+cookieArray[i]);
				cookie = cookieArray[i];
				customCookie = cookieArray[i].getValue();
			}
		}else{
			out.print("\nCookie[] is null.");
		}
		
		if(cookie != null){
			out.print("\nCookie: "+ customCookie);
		}else{
			Integer maxAge = Integer.parseInt(getServletContext().getInitParameter("cookieAge"));
			out.print(getServletContext().getInitParameter("cookieAge").getClass());
			out.print("\nMax age from contex param: "+ maxAge);
			cookie = new Cookie("customCookie", "custom cookie value");
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
			out.print("\nNew Cookie created.");
		}
	}
}
