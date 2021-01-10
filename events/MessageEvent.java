package com.gil.gamesite.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.gil.gamesite.enums.EventType;
import com.gil.gamesite.messages.bean.Message;

public class MessageEvent extends ApplicationEvent {


	private static final long serialVersionUID = 1L;
	
	private EventType eventType;
	
	@Autowired
	private Message message;
	
	public MessageEvent(Object source , Message message,EventType type) {
		super(source);
		this.eventType = type;
		this.message = message;
	}

	public EventType getEventType() {
		return eventType;
	}

	public Message getMessage() {
		return message;
	}

}
