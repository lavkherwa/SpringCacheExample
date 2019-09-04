
package com.example.cache.cacheUtils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.github.benmanes.caffeine.cache.stats.CacheStats;

public class CacheUtils {

	/**
	 * Return a common, provider-neutral interface for obtaining cache statistics
	 *
	 * @param cacheManager the cache manager holding the target cache
	 * @param cacheName    the name of the target cache
	 * @return a common, provider-neutral interface for obtaining cache statistics
	 */
	public static ICacheStats getCacheStats(final CacheManager cacheManager, final String cacheName) {
		final Cache cache = cacheManager.getCache(cacheName);
		if (null == cache)
			return getStatsNoOpCache(cacheName + " (not found)");

		final Object objCache = cache.getNativeCache();
		if (objCache instanceof com.github.benmanes.caffeine.cache.Cache)

			return getStatsCaffeineCache(cacheName, objCache);

		// if (objCache instanceof NoOpCache)
		// or any other case
		return getStatsNoOpCache(cacheName);
	}

	/**
	 * @param cacheName the name of the cache
	 */
	private static ICacheStats getStatsNoOpCache(final String cacheName) {
		return new ICacheStats() {

			@Override
			public String getCacheName() {
				return cacheName;
			}

			@Override
			public long getLookups() {
				return -1;
			}

			@Override
			public long getHits() {
				return -1;
			}

			@Override
			public long getMisses() {
				return -1;
			}

			@Override
			public long getRefreshes() {
				return -1;
			}

			@Override
			public long getRefreshTotalTime() {
				return -1;
			}

			@Override
			public long getCurrentSize() {
				return -1;
			}

			@Override
			public long getErrors() {
				return -1;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				final String txt = String.format("%s: no-ops cache.", getCacheName());
				return txt;
			}
		};
	}

	/**
	 * @param cacheName the name of the cache
	 * @param objCache  the cache
	 */
	private static ICacheStats getStatsCaffeineCache(final String cacheName, final Object objCache) {
		@SuppressWarnings("rawtypes")
		final com.github.benmanes.caffeine.cache.Cache nativeCache = (com.github.benmanes.caffeine.cache.Cache) objCache;
		final CacheStats stats = nativeCache.stats();
		if (null != stats)
			return new ICacheStats() {

				// snapshot the statistics values
				private final long refreshes = stats.evictionCount();
				private final long refreshTotalTime = stats.totalLoadTime();
				private final long misses = stats.missCount();
				private final long hits = stats.hitCount();
				private final long errors = stats.loadFailureCount();
				private final long size = nativeCache.estimatedSize();
				private final long lookups = stats.requestCount();

				@Override
				public long getRefreshes() {
					return refreshes;
				}

				@Override
				public long getRefreshTotalTime() {
					return refreshTotalTime;
				}

				@Override
				public long getMisses() {
					return misses;
				}

				@Override
				public long getHits() {
					return hits;
				}

				@Override
				public long getErrors() {
					return errors;
				}

				@Override
				public long getCurrentSize() {
					return size;
				}

				@Override
				public String getCacheName() {
					return cacheName;
				}

				@Override
				public long getLookups() {
					return lookups;
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.lang.Object#toString()
				 */
				@Override
				public String toString() {
					final String txt = String.format("%s: %d hits, %d misses, %d errors. %d elements in cache.",
							getCacheName(), getHits(), getMisses(), getErrors(), getCurrentSize());
					return txt;
				}

			};
		else
			// return "no stats"
			return getStatsNoOpCache(cacheName + " (no stats)");
	}

}
