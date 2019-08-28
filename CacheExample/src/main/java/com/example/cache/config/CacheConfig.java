package com.example.cache.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.cache.cacheImpl.CustomCaffeineCache;
import com.example.cache.pojo.ILoggedOnUser;
import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

	@Bean
	public CacheManager cacheManager(@Autowired ILoggedOnUser loggedOnUser) {

		// return new ConcurrentMapCacheManager("resources");

		final SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

		final Cache cacheResources;
		cacheResources = new CaffeineCache("resources", Caffeine.newBuilder()//
				.expireAfterAccess(10, TimeUnit.SECONDS)//
				.recordStats()//
				.softValues()//
				.build());

		final Cache cacheUserSpecific;
		cacheUserSpecific = new CustomCaffeineCache("user-specific-cache", loggedOnUser, Caffeine.newBuilder()//
				.expireAfterAccess(10, TimeUnit.SECONDS)//
				.recordStats()//
				.softValues()//
				.build());

		final Collection<? extends Cache> caches = Arrays.asList(cacheResources, cacheUserSpecific);
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}

}
