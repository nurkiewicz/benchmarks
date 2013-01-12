package com.blogspot.nurkiewicz.cacheable.calculator;

import com.blogspot.nurkiewicz.cacheable.calculator.Calculator;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 1:45 PM
 */
public class ManuallyInstrumentedCalculator implements Calculator {
	@Override
	public int identity(int x) {
		return x;
	}
}
