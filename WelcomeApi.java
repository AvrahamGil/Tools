package com.gil.gamesite.api;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gil.gamesite.bean.LoginDetails;
import com.gil.gamesite.bean.User;
import com.gil.gamesite.enums.ErrorType;
import com.gil.gamesite.enums.LoginState;
import com.gil.gamesite.enums.SessionState;
import com.gil.gamesite.enums.State.UserState;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.logic.Login;
import com.gil.gamesite.logic.UserLogic;
import com.gil.gamesite.utils.SessionUtils;

@RestController
@RequestMapping("/WelcomeApi")
public class WelcomeApi {

	@Autowired
	private Login login;
	
	@Autowired
	private UserLogic userLogic;
	
	@RequestMapping(value="/register" , method = RequestMethod.POST)
	public void createUser(@RequestBody User user) throws Exception {
		 userLogic.base(user,UserState.Register);
	}
	
	@RequestMapping(value = "/login" , method=RequestMethod.POST)
	public void login(@RequestBody LoginDetails loginDetail,HttpServletRequest request) throws ApplicationException, FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
		 login.validateLoginDetails(loginDetail, request, LoginState.Login);
		
	}
	
	@RequestMapping(value = "/forgotPassword" , method=RequestMethod.POST)
	public void forgotPassword(@RequestBody LoginDetails loginDetail,HttpServletRequest request) throws ApplicationException, FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
			login.forgotPasswordDetails(loginDetail, request, LoginState.ForgotPassword);
	}
	
	
}
