package com.gil.gamesite.events;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gil.gamesite.dao.PostDao;
import com.gil.gamesite.enums.LogFilePath;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.utils.LogHandler;



@Service
public class PostListener implements ApplicationListener<PostEvent> {

	@Autowired
	private PostDao postDao;
	
	@Async
	public void onApplicationEvent(PostEvent post){
		try {
			switch(post.getEventType()) {
			case Add:
				postDao.newPost(post.getPosts());
				LogHandler.infoLogHandler(LogFilePath.LogicCreateLogFile.getFilePath(),
						"Base - Create post successfully for user {" + post.getPosts().getUserName() + "} ");
				break;
			case Edit:
				postDao.editPost(post.getPosts().getPostId(), post.getPosts().getContent());
				LogHandler.infoLogHandler(LogFilePath.LogicUpdateLogFile.getFilePath(),
						"Base - Update post successfully for user {" + post.getPosts().getUserName() + "} ");
				break;
			case Delete:
				postDao.deletePost(post.getPosts().getPostId());
				LogHandler.infoLogHandler(LogFilePath.LogicDeleteLogFile.getFilePath(),
						"Base - Delete post successfully for user {" + post.getPosts().getUserName() + "} ");
				break;	
			}
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
	}

}
