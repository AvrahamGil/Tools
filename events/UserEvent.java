package com.gil.gamesite.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.gil.gamesite.bean.User;
import com.gil.gamesite.enums.EventType;

public class UserEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private User user;

	private EventType eventType;
	
	public UserEvent(Object source, User user, EventType eventType) {
		super(source);
		this.user = user;
		this.eventType = eventType;
	}

	public User getUser() {
		return user;
	}

	public EventType getEventType() {
		return eventType;
	}

	
}
