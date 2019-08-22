package com.example.cacheImp;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TargetResource {

	@Cacheable(value = "resources", key = "#resourceId")
	public String getResource(Long resourceId) {
		System.out.println("Resource is picked from the repo for resource Id: " + resourceId);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "This resource is so useful!!, Resource id is: " + resourceId;
	}
	
}
