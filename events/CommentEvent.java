package com.gil.gamesite.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.gil.gamesite.bean.Comment;
import com.gil.gamesite.enums.EventType;

public class CommentEvent extends ApplicationEvent{


	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Comment comment;
	
	private EventType eventType;
	
	public CommentEvent(Object source, EventType eventType,Comment comment) {
		super(source);
		this.eventType = eventType;
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}

	public EventType getEventType() {
		return eventType;
	}

}
