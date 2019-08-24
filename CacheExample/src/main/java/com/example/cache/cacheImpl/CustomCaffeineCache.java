package com.example.cache.cacheImpl;

import java.util.Objects;
import java.util.concurrent.Callable;

import org.springframework.cache.caffeine.CaffeineCache;

import com.example.cache.pojo.ILoggedOnUser;
import com.github.benmanes.caffeine.cache.Cache;

public class CustomCaffeineCache extends CaffeineCache {

	private final ILoggedOnUser userInformation;

	public CustomCaffeineCache(String name, ILoggedOnUser userInfoProvider, Cache<Object, Object> cache,

			boolean allowNullValues) {

		super(name, cache, allowNullValues);
		Objects.requireNonNull(userInfoProvider);
		Objects.requireNonNull(cache);
		this.userInformation = userInfoProvider;

	}

	public CustomCaffeineCache(String name, ILoggedOnUser userInfoProvider, Cache<Object, Object> cache) {

		super(name, cache);
		Objects.requireNonNull(userInfoProvider);
		Objects.requireNonNull(cache);
		this.userInformation = userInfoProvider;

	}

	@Override

	public ValueWrapper get(final Object key) {

		final Object decoratedKey = decorateObjectKey(key);
		return super.get(decoratedKey);

	}

	@Override

	public <T> T get(final Object key, final Callable<T> valueLoader) {

		final Object decoratedKey = decorateObjectKey(key);
		return super.get(decoratedKey, valueLoader);

	}

	@Override

	public <T> T get(final Object key, final Class<T> type) {

		final Object decoratedKey = decorateObjectKey(key);

		return super.get(decoratedKey, type);

	}

	@Override

	protected Object lookup(final Object key) {

		// This would lead to double-decoration
		// final Object decoratedKey = decorateObjectKey(key);
		return super.lookup(key);

	}

	@Override

	public void put(final Object key, final Object value) {

		final Object decoratedKey = decorateObjectKey(key);
		super.put(decoratedKey, value);

	}

	@Override

	public ValueWrapper putIfAbsent(final Object key, final Object value) {

		final Object decoratedKey = decorateObjectKey(key);
		return super.putIfAbsent(decoratedKey, value);

	}

	@Override

	public void evict(final Object key) {

		final Object decoratedKey = decorateObjectKey(key);
		super.evict(decoratedKey);

	}

	protected Object decorateObjectKey(final Object key) {

		/*- Key should comprise of additional user parameters
		*  user -> Id and Email
		* */

		final String decoratedKey = userInformation.getId() + userInformation.getEmail() + String.valueOf(key);
		return decoratedKey;

	}

}
