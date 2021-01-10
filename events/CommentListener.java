package com.gil.gamesite.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gil.gamesite.dao.CommentDao;
import com.gil.gamesite.enums.LogFilePath;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.utils.LogHandler;

@Service
public class CommentListener implements ApplicationListener<CommentEvent>{

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void onApplicationEvent(CommentEvent comment) {
		try {
			switch(comment.getEventType()) {
			case Add:
				commentDao.addComment(comment.getComment());
				LogHandler.infoLogHandler(LogFilePath.LogicCreateLogFile.getFilePath(),
						"Base - Create comment successfully for user {" + comment.getComment().getUserName() + "} ");
				break;
			case Edit:
				commentDao.updateCommentDetails(comment.getComment());
				LogHandler.infoLogHandler(LogFilePath.LogicUpdateLogFile.getFilePath(),
						"Base - Update comment successfully for user {" +  comment.getComment().getUserName() + "} ");
				break;
			case Delete:
				commentDao.deleteComment(comment.getComment().getCommentId());
				LogHandler.infoLogHandler(LogFilePath.LogicDeleteLogFile.getFilePath(),
						"Base - Delete comment successfully for user {" + comment.getComment().getUserName() + "} ");
				break;	
			}
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
		
	}

}
