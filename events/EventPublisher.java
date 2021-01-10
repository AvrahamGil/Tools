package com.gil.gamesite.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gil.gamesite.bean.Comment;
import com.gil.gamesite.bean.Post;
import com.gil.gamesite.bean.User;
import com.gil.gamesite.enums.EventType;
import com.gil.gamesite.enums.LogFilePath;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.messages.bean.Message;

@Service
public class EventPublisher implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher publisher;
     
    //You must override this method; It will give you access to ApplicationEventPublisher
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    
    public User newUserEvenet(User user, EventType eventType) {
    	publisher.publishEvent(new UserEvent(this, user ,eventType));
    	return user;
    }
    
    public Post newPostEvent(Post post,EventType eventType)
    {  
        publisher.publishEvent(new PostEvent(this, eventType, post));
        return post;
    }
	
    public Message newMessageEvent(Message message,EventType eventType) {
    	publisher.publishEvent(new MessageEvent(this,message, eventType));
    	return message;
    }
        
    public Comment newCommentEvent(Comment comment,EventType eventType) {
    	publisher.publishEvent(new CommentEvent(this, eventType, comment));
    	return comment;
    }
    
    public Exception newExceptionEvent(Exception exception) {
    	publisher.publishEvent(new ExceptionEvent(this,exception));
    	return exception;
    }
    
    public ApplicationException newApplicationExceptionEvent(ApplicationException exception) {
    	publisher.publishEvent(new ApplicationExceptionEvent(this,exception));
    	return exception;
    }
}
