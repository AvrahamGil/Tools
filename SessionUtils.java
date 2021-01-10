package com.gil.gamesite.utils;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gil.gamesite.bean.LoginDetails;
import com.gil.gamesite.enums.SessionState;
import com.gil.gamesite.exceptions.ApplicationException;

public class SessionUtils {

	private static HttpSession session;
	
	public static HttpSession createSession(HttpServletRequest request,LoginDetails loginDetails, SessionState state) throws NoSuchAlgorithmException, ApplicationException  {
		switch(state) {
		case Create:
			session = request.getSession(true);
			session.setAttribute("token", TokenBuilder.generatedToken(loginDetails).toString());
			session.setMaxInactiveInterval(15*60);
			System.out.println(session.getAttribute("token"));
			return session;
		}
		return null;
	}
	
	public static HttpSession verifyToken(HttpServletRequest request,SessionState state) throws NoSuchAlgorithmException, ApplicationException {
		session = request.getSession(false);
		switch(state) {
		case Verify:
			if(session != null && TokenBuilder.verifyToken(session.getAttribute("token").toString()))
				return session;
			break;
		case Delete:
			if(session != null && session.getCreationTime() <= System.currentTimeMillis())
				session.invalidate();
			break;
		}
		return null;
	}
}
