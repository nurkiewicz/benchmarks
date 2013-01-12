package com.blogspot.nurkiewicz.cacheable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 1:33 PM
 */
public aspect ManualCachingAspect {

	private final Map<Integer, Integer> cache = new ConcurrentHashMap<Integer, Integer>();

	pointcut cacheMethodExecution(int x): execution(int com.blogspot.nurkiewicz.cacheable.calculator.ManuallyInstrumentedCalculator.identity(int)) && args(x);

	Object around(int x): cacheMethodExecution(x) {
		final Integer existing = cache.get(x);
		if (existing != null) {
			return existing;
		}
		final Object newValue = proceed(x);
		cache.put(x, (Integer)newValue);
		return newValue;
	}

}
