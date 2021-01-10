package com.gil.gamesite.events;

import org.springframework.context.ApplicationEvent;
import com.gil.gamesite.enums.EventType;


public class ExceptionEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private Exception exception;
	
	public ExceptionEvent(Object source,Exception exception) {
		super(source);
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

}
