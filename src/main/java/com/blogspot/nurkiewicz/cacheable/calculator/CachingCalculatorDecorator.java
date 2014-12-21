package com.blogspot.nurkiewicz.cacheable.calculator;

import java.util.Map;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 4:59 PM
 */
public class CachingCalculatorDecorator implements Calculator {

	private final Map<Integer, Integer> cache = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();

	private final Calculator target;

	public CachingCalculatorDecorator(Calculator target) {
		this.target = target;
	}

	@Override
	public int identity(int x) {
		final Integer existing = cache.get(x);
		if (existing != null) {
			return existing;
		}
		final int newValue = target.identity(x);
		cache.put(x, newValue);
		return newValue;
	}
}
