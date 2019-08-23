package com.example.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cache.pojo.ILoggedOnUser;
import com.example.cache.pojo.LoggedOnUser;

@Configuration
public class LoggedOnUserConfig {

	@Bean
	public ILoggedOnUser loggedOnUser() {
		LoggedOnUser user = new LoggedOnUser();
		user.setId(01);
		user.setEmail("abc.@java.com");

		return user;
	}

}
