package com.gil.gamesite.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.gil.gamesite.dao.UserDao;
import com.gil.gamesite.enums.LogFilePath;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.utils.LogHandler;

@Service
public class UserListener implements ApplicationListener<UserEvent>{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void onApplicationEvent(UserEvent user) {
		try {
			switch(user.getEventType()) {
			case Add:
				userDao.createUser(user.getUser());
				
				LogHandler.infoLogHandler(LogFilePath.LogicCreateLogFile.getFilePath(), "Base - Create user {" + user.getUser().getEmail() +"} successfully");
				
				break;
			case Edit:
				userDao.updateUserDetails(user.getUser());
				
				LogHandler.infoLogHandler(LogFilePath.LogicUpdateLogFile.getFilePath(), "Base - Update user {" + user.getUser().getEmail() +"} successfully");
				break;
			case Delete:
				userDao.deactivateUser(user.getUser().getUserName(), user.getUser().getPassword());
				
				LogHandler.infoLogHandler(LogFilePath.LogicDeleteLogFile.getFilePath(), "Base - Deactivate user {" + user.getUser().getEmail() +"} successfully");
				break;	
			}
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
		
	}

}
