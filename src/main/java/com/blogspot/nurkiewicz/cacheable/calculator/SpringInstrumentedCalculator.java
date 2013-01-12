package com.blogspot.nurkiewicz.cacheable.calculator;

import org.springframework.cache.annotation.Cacheable;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 12:36 PM
 */
public class SpringInstrumentedCalculator implements Calculator {

	@Override
	@Cacheable("identity")
	public int identity(int x) {
		return x;
	}

}
