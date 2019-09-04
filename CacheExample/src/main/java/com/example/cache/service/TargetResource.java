package com.example.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.cache.config.CacheConfig;

@Component
public class TargetResource {

	public static final String KEY = "dummy_key";

	/*
	 * Note: you can either provide key directly by specifying SPEL expression or
	 * let your keyGenerator take care of it
	 */
	@Cacheable(value = "resources", keyGenerator = CacheConfig.CUSTOM_KEYGENERATOR)
	public String getResource(Long resourceId) {
		System.out.println("Resource is picked from the repo for resource Id: " + resourceId);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "This resource is so useful!!, Resource id is: " + resourceId;
	}

	/* Example on how to enable cache on a method without parameter */
	@Cacheable(value = "resources", key = "#root.target.KEY")
	public String getResource2() {
		System.out.println("Resource is picked from the repo without resourceId");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "This resource is so useful!!";
	}

	@Cacheable(value = "user-specific-cache", key = "#root.target.KEY")
	public String getResource3() {
		System.out.println("Resource is picked from the repo for loggedOn user");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "This resource is so useful!! for logged on user";
	}

}
