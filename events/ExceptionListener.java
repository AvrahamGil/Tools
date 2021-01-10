package com.gil.gamesite.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.exceptions.ExceptionHandler;

@Service
public class ExceptionListener implements ApplicationListener<ExceptionEvent>{

	@Override
	public void onApplicationEvent(ExceptionEvent exception) {
		try {
			ExceptionHandler.generatedDaoExceptions(exception.getException(),
					exception.getException().getMessage());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}

}
