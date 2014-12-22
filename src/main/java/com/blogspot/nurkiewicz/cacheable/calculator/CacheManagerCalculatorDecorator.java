package com.blogspot.nurkiewicz.cacheable.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class CacheManagerCalculatorDecorator implements Calculator {

	private final Calculator target;

	@Autowired
	private CacheManager cacheManager;

	public CacheManagerCalculatorDecorator(Calculator target) {
		this.target = target;
	}

	@Override
	public int identity(int x) {

		final Cache cache = cacheManager.getCache("identity");

		final Cache.ValueWrapper existing = cache.get(x);
		if (existing != null) {
			return (int) existing.get();
		}

		final int newValue = target.identity(x);
		cache.put(x, newValue);
		return newValue;
	}
}
