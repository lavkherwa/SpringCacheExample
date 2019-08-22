package com.example.cacheImp.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {

		// return new ConcurrentMapCacheManager("resources");

		final SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		final Cache cacheResources;

		cacheResources = new CaffeineCache("resources", Caffeine.newBuilder()//
				.expireAfterAccess(10, TimeUnit.SECONDS)//
				.recordStats()//
				.softValues()//
				.build(), //
				true);

		final Collection<? extends Cache> caches = Arrays.asList(cacheResources);
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}

}
