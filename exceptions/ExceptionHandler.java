package com.gil.gamesite.exceptions;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

import com.gil.gamesite.enums.ErrorType;
import com.gil.gamesite.enums.LogFilePath;
import com.gil.gamesite.utils.LogHandler;

public class ExceptionHandler {

	public static Exception generatedLogicExceptions(ApplicationException e, String logException)
			throws ApplicationException {
		LogHandler.serverLogHandler(LogFilePath.ExceptionLogicLogFile.getFilePath(), logException);
		List<ApplicationException> exceptions = new ArrayList<ApplicationException>();
		exceptions.add(e);

		for (ApplicationException exception : exceptions) {

			if (exception.getErrorType().equals(ErrorType.Login_Error)) {
				throw new ApplicationException(ErrorType.General_Error, "User name or password incorrect", true);
			}
			if (exception.getErrorType().equals(ErrorType.Create_Failed)) {
				throw new ApplicationException(ErrorType.General_Error, "One or more details are incorrect", true);
			}
			if (exception.getErrorType().equals(ErrorType.Update_Failed)) {
				throw new ApplicationException(ErrorType.General_Error, "Check your details again.", true);
			}
			if (exception.getErrorType().equals(ErrorType.Deactivate_Failed)) {
				throw new ApplicationException(ErrorType.General_Error, "User already deactivate, please check again.",
						true);
			}
			if (exception.getErrorType().equals(ErrorType.Validate_Error)) {
				throw new ApplicationException(ErrorType.General_Error, "One or more details are incorrect", true);
			}
			if (exception.getErrorType().equals(ErrorType.Gift_Dosent_Exist)) {
				throw new ApplicationException(ErrorType.General_Error, "Gift dosen't exist", true);
			}
			if (exception.getErrorType().equals(ErrorType.User_Details)) {
				throw new ApplicationException(ErrorType.General_Error, "One or more details are incorrect", true);
			}
			if (exception.getErrorType().equals(ErrorType.Store_Empty)) {
				throw new ApplicationException(ErrorType.General_Error, "No such gifts", true);
			}
			if (exception.getErrorType().equals(ErrorType.Inventory)) {
				throw new ApplicationException(ErrorType.General_Error, "No such gifts", true);
			}
			if (exception.getErrorType().equals(ErrorType.DaoException)) {
				throw new ApplicationException(ErrorType.General_Error, "One or more details are incorrect", true);
			}
		}
		throw new ApplicationException(ErrorType.General_Error, e.getMessage(),true);

	}

	public static Exception generatedDaoExceptions(Exception e, String logException) throws ApplicationException {
		LogHandler.serverLogHandler(LogFilePath.ExceptionDaoLogFile.getFilePath(), logException);
		List<Exception> exceptions = new ArrayList<Exception>();
		exceptions.add(e);
		for (Exception exception : exceptions) {
			if (exception.getClass().equals(DataException.class))
				throw new ApplicationException(ErrorType.DaoException, "Details are not spell correctly", true);
			if (exception.getClass().equals(NoResultException.class))
				throw new ApplicationException(ErrorType.DaoException, "No such data", true);
			if(exception.getClass().equals(ConstraintViolationException.class))
				throw new ApplicationException(ErrorType.DaoException, "Details are incorrect", true);
		}

		throw new ApplicationException(ErrorType.DaoException, e.getMessage(), true);
	}

	
}
