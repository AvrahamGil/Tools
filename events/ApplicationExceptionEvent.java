package com.gil.gamesite.events;

import org.springframework.context.ApplicationEvent;

import com.gil.gamesite.enums.EventType;
import com.gil.gamesite.exceptions.ApplicationException;

public class ApplicationExceptionEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private ApplicationException applicationException;
	
	public ApplicationExceptionEvent(Object source,ApplicationException applicationException) {
		super(source);
		this.applicationException = applicationException;
	}

	public ApplicationException getApplicationException() {
		return applicationException;
	}
}
