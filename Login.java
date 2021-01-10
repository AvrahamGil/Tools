package com.gil.gamesite.logic;

import java.io.FileNotFoundException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gil.gamesite.bean.LoginDetails;
import com.gil.gamesite.bean.User;
import com.gil.gamesite.enums.ErrorType;
import com.gil.gamesite.enums.LoginState;
import com.gil.gamesite.enums.MailType;
import com.gil.gamesite.enums.SessionState;
import com.gil.gamesite.events.EventPublisher;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.exceptions.ExceptionHandler;
import com.gil.gamesite.security.logic.ValidateUser;
import com.gil.gamesite.utils.SessionUtils;

@Service
public class Login {

	@Autowired
	private ValidateUser validateUser;

	@Autowired
	private MailLogic mailLogic;

	private HttpSession session;

	@Autowired
	private EventPublisher eventPublisher;

	public HttpSession validateLoginDetails(LoginDetails loginDetails, HttpServletRequest request, LoginState loginState)
			throws ApplicationException, FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
		try {
			validateUser.loginUser(loginDetails.getUserName(), loginDetails.getUserPassword());
			
			switch (loginState) {
			case Login:
				session = SessionUtils.createSession(request, loginDetails,
						SessionState.Create);
				return session;
			case Logout:
				session = request.getSession(false);
				if (session != null && session.getAttribute("userName").toString().equals(loginDetails))
					session.invalidate();
				return null;
			case ForgotPassword:
				break;
			}
		} catch (ApplicationException e) {
			eventPublisher.newApplicationExceptionEvent(e);
		}

		return null;
	}

	public void forgotPasswordDetails(LoginDetails loginDetail, HttpServletRequest request, LoginState loginState)
			throws ApplicationException {
		session = request.getSession(false);
		String password = loginDetail.getUserPassword();

		try {
			switch (loginState) {
			case ForgotPassword:
				if (session == null) {
					password = validateUser.validateForgotPasswordDetails(loginDetail.getEmail(),
							loginDetail.getUserName());

						mailLogic.sendEmail(loginDetail.getEmail(), MailType.RecoverPassword,
								MailType.RecoverPassword.getMailType() + password);
				}

				break;
			}
		} catch (ApplicationException e) {
			eventPublisher.newApplicationExceptionEvent(e);
		}
	}
}
