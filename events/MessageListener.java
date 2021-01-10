package com.gil.gamesite.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.messages.dao.MessageDao;


@Service
public class MessageListener implements ApplicationListener<MessageEvent> {

	@Autowired
	private MessageDao messageDao;
	
	public void onApplicationEvent(MessageEvent message) {
		try {
			switch(message.getEventType()) {
			case Add:
				messageDao.sendMessage(message.getMessage());
				break;
			case Edit:
				messageDao.editMessage(message.getMessage());
				break;
			case Delete:
				messageDao.deleteMessges(message.getMessage().getMessageId());
				break;	
			}
		}catch(ApplicationException e) {
			e.printStackTrace();
		}

	}

}

