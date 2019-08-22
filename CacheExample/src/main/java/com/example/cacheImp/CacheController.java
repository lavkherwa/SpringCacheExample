package com.example.cacheImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class CacheController {

	@Autowired
	TargetResource resourcePool;

	@GetMapping(value = "/{id}")
	public String getMessage(@PathVariable("id") Long id) {		
		return resourcePool.getResource(id);
	}

}
