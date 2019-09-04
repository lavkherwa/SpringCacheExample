package com.example.cache.cacheUtils;

/**
 * Interface for getting statistics about caches.
 * <p>
 * The interface is neutral to the concrete cache service provider
 * implementation.
 *
 */
public interface ICacheStats {

	/** @return the name of the cache that produced these statistics */
	String getCacheName();

	/** @return number of total cache lookups */
	long getLookups();

	/** @return number of successful cache lookups */
	long getHits();

	/** @return number of cache misses */
	long getMisses();

	/** @return number of refreshes on the cached data */
	long getRefreshes();

	/** @return total time for refreshing cached elements */
	long getRefreshTotalTime();

	/** @return the current number of cached elements */
	long getCurrentSize();

	/** @return number of errors during cache refreshes */
	long getErrors();
}
