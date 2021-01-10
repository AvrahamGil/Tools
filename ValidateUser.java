package com.gil.gamesite.security.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gil.gamesite.bean.User;
import com.gil.gamesite.enums.ActivateType;
import com.gil.gamesite.enums.ErrorType;
import com.gil.gamesite.enums.Gender;
import com.gil.gamesite.enums.MailType;
import com.gil.gamesite.enums.PrimitiveTypes;
import com.gil.gamesite.enums.State.UserState;
import com.gil.gamesite.events.EventPublisher;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.exceptions.ExceptionHandler;
import com.gil.gamesite.logic.MailLogic;
import com.gil.gamesite.security.dao.ValidateUserDetailsDao;
import com.gil.gamesite.utils.Countries;
import com.gil.gamesite.utils.RandomHelper;

@Service
public class ValidateUser {

	@Autowired
	private ValidateUserDetailsDao userDetailsDao;

	@Autowired
	private EventPublisher eventPublisher;
	
	private User user = null;

	private static int minLetters = 6;

	private static int maxLetters = 50;

	private static final int MAX_NUMBERS = 3;

	private static String pattern = "[A-Za-z0-9]+";

	private static String emailPattern = "^[A-Za-z0-9+_.-]+@[a-z]$";

	private List<String> userDetails = new ArrayList<String>();

	
	// Login
	public User loginUser(String userName, String userPassword) throws ApplicationException {
		userDetails.add(userName);
		userDetails.add(userPassword);

		try {
			if (validateUserDetails(userDetails))
				user = userDetailsDao.getUserDetails(userName, userPassword);

			if (user == null)
				throw new ApplicationException(ErrorType.Login_Error, "Details are not correct", false);

			return user;
			
		} catch (ApplicationException e) {
			eventPublisher.newApplicationExceptionEvent(e);
		} finally {
			userDetails.clear();
		}
		return null;

	}

	public String validateForgotPasswordDetails(String email, String userName) throws ApplicationException {
		userDetails.add(email);
		userDetails.add(userName);

		try {
			if (!validateUserDetails(userDetails))
				throw new ApplicationException(ErrorType.Validate_Error, "User name or email not spell correctly",
						false);
			String password = userDetailsDao.validateForgotPasswordDetails(email,userName);
			
			return password;
			
		} catch (ApplicationException e) {
			eventPublisher.newApplicationExceptionEvent(e);
		} finally {
			userDetails.clear();
		}
		return null;
	}
	
	// For new user
	public boolean validateDetails(User user, UserState state) throws ApplicationException {
		userDetails.add(user.getUserName());
		userDetails.add(user.getPassword());
		// user.getEmail().matches(emailPattern)
		try {
			if (validateUserDetails(userDetails) != true)
				throw new ApplicationException(ErrorType.Validate_Error, "Identity details are incorrect", false);

			if (isCountryExist(user.getCountry()) != true && user.getGender().equals(Gender.Male.getGender())
					|| user.getGender().equals(Gender.Female.getGender()))
				throw new ApplicationException(ErrorType.Validate_Error, "Personal details are incorrect", false);

			if ((userDetailsDao.isUserNameOrEmailAlreadyInUse(user.getUserName(), user.getEmail()) != null))
				throw new ApplicationException(ErrorType.Validate_Error, "User details already in use", false);

			if (state.equals(UserState.Register)) {
				user.setActivate(ActivateType.Activate.toString());
				user.setId(generateUserId());
			}

			return true;
		} catch (ApplicationException e) {
			eventPublisher.newApplicationExceptionEvent(e);
		} finally {
			userDetails.clear();
		}
		return false;
	}

	private boolean validateUserDetails(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
			if (!(minLetters <= words.get(i).length() && words.get(i).length() <= maxLetters))
				return false;
		}

		for (int i = 0; i < words.size(); i++) {
			if (!words.get(i).matches(pattern))
				return false;
		}

		return true;
	}

	private boolean isCountryExist(String country) {
		if (Countries.isCountryExist(country))
			return true;
		return false;
	}

	private String generateUserId() {
		return RandomHelper.randomHelper(PrimitiveTypes.Integer, MAX_NUMBERS);
	}
}
