package com.gil.gamesite.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;


import com.gil.gamesite.bean.Post;
import com.gil.gamesite.enums.EventType;

public class PostEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private EventType eventType;

	@Autowired
	private Post post;

	// Constructor's first parameter must be source
	public PostEvent(Object source, EventType eventType, Post post) {
		// Calling this super class constructor is necessary
		super(source);
		this.eventType = eventType;
		this.post = post;
	}

	public EventType getEventType() {
		return eventType;
	}

	public Post getPosts() {
		return post;
	}

	
}
