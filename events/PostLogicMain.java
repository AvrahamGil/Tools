package com.gil.gamesite.events;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.gil.gamesite.bean.Post;
import com.gil.gamesite.enums.State.PostState;
import com.gil.gamesite.exceptions.ApplicationException;
import com.gil.gamesite.logic.PostLogic;


@SpringBootApplication
@ComponentScan(basePackages = { "com.gil.gamesite" })
public class PostLogicMain  {

	private static PostLogic postLogic;

	private static Post post;

	/*
	public static void main(String[] args) throws InterruptedException, ApplicationException {
		SpringApplication sa = new SpringApplication(PostLogicMain.class);
		ConfigurableApplicationContext context = sa.run(args);

		post = context.getBean(Post.class);
		postLogic = (PostLogic) context.getBean(PostLogic.class);

		post = generatedPostDetails();
		postLogic.baseFunction(post, "Gilush1", "Gilsome1", PostState.Create);

	}

	private static Post generatedPostDetails() {
		post.setPostId("999");
		post.setFullName("Gila");
		post.setContent("Event Test");
		post.setTitle("Event");
		post.setUserName("Gilush1");
		
		return post;
	}

*/
//	public void run(String... args) throws Exception {
//		String[] beans = applicationContext.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String bean : beans) {
//			System.out.println(bean);
//
//		}
//	}
}
