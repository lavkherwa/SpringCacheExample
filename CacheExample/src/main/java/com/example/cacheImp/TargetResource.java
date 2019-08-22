package com.example.cacheImp;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TargetResource {

	public static final String KEY = "dummy_key";

	@Cacheable(value = "resources", key = "#resourceId")
	public String getResource(Long resourceId) {
		System.out.println("Resource is picked from the repo for resource Id: " + resourceId);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "This resource is so useful!!, Resource id is: " + resourceId;
	}

	/* Example on how to enable cache a method without parameter */
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

}
