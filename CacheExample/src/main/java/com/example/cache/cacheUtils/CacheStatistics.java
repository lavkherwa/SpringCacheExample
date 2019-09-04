
package com.example.cache.cacheUtils;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class CacheStatistics {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheStatistics.class);

	@Autowired
	private CacheManager cacheManager;

	/**
	 * Finally log cache statistics at shutdown time.
	 */
	@PreDestroy
	public void logCacheStatistics() {
		if (cacheManager != null) {
			final StringBuilder sb = new StringBuilder("Cache statistics");
			for (final String cacheName : cacheManager.getCacheNames()) {
				final ICacheStats stats = CacheUtils.getCacheStats(cacheManager, cacheName);
				if (null != stats) {
					final String s = String.format(
							"%n  %s: %s current elements, %d lookups, %d hits, %d misses, %d refreshes, %d errors", //
							stats.getCacheName(), stats.getCurrentSize(), stats.getLookups(), stats.getHits(),
							stats.getMisses(), stats.getRefreshes(), stats.getErrors());
					sb.append(s);
				}
			}
			LOGGER.info(sb.toString());
		}
	}
}
