package com.gil.gamesite.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.exceptions.ExceptionHandler;

@Service
public class ApplicationExceptionListener implements ApplicationListener<ApplicationExceptionEvent> {

	@Override
	public void onApplicationEvent(ApplicationExceptionEvent exception) {
		try {
			ExceptionHandler.generatedLogicExceptions(exception.getApplicationException(),
					exception.getApplicationException().getMessage());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

}
