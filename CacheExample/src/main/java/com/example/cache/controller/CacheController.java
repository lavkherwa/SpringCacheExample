package com.example.cache.controller;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.cacheUtils.CacheUtils;
import com.example.cache.cacheUtils.ICacheStats;
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
		final StringBuilder sb = new StringBuilder("Cache statistics");
		if (cacheManager != null) {
			for (final String cacheName : cacheManager.getCacheNames()) {
				final ICacheStats stats = CacheUtils.getCacheStats(cacheManager, cacheName);
				if (null != stats) {
					final String s = String.format(
							"\n %s: %s current elements, %d lookups, %d hits, %d misses, %d refreshes, %d errors", //
							stats.getCacheName(), stats.getCurrentSize(), stats.getLookups(), stats.getHits(),
							stats.getMisses(), stats.getRefreshes(), stats.getErrors());
					sb.append(s);
				}
			}
		}
		return sb.toString();
	}

}
