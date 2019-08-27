package com.example.cache.controller;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.service.TargetResource;

@RestController
@RequestMapping("/resource")
public class CacheController {

	final TargetResource resourcePool;
	final CacheManager cacheManager;

	/*- Auto-wire using constructor 
	 * 
	 * Avoid field auto-wiring using @Autowired annotation
	 * and instead used constructor based auto-wiring
	 * 
	 */
	public CacheController(TargetResource resourcePool, CacheManager cacheManager) {
		this.resourcePool = resourcePool;
		this.cacheManager = cacheManager;
	}

	@GetMapping(value = "/{id}")
	public String getMessage(@PathVariable("id") Long id) {
		return resourcePool.getResource(id);
	}

	@GetMapping(value = "/user")
	public String getMessage() {
		return resourcePool.getResource3();
	}

	@GetMapping
	public String getMessage2() {
		return resourcePool.getResource2();
	}

	@GetMapping(value = "/stats")
	public String getStats() {
		String message = "";
		Cache userCache = (Cache) cacheManager.getCache("user-specific-cache");
		if (null != userCache) {
			final Object objCache = userCache.getNativeCache();
			if (objCache instanceof com.github.benmanes.caffeine.cache.Cache) {

				@SuppressWarnings("rawtypes")
				final com.github.benmanes.caffeine.cache.Cache nativeCache = (com.github.benmanes.caffeine.cache.Cache) objCache;
				

				message = "Cache is hit: " + nativeCache.stats().hitCount() + " times, \n" + //
						"Cache is evicted: " + nativeCache.stats().evictionCount() + " times, \n" + //
						"Cache is missed: " + nativeCache.stats().missCount() + " times.";
			}
		}

		return message;
	}

}
