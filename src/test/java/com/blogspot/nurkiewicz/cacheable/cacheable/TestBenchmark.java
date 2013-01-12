package com.blogspot.nurkiewicz.cacheable.cacheable;

import com.blogspot.nurkiewicz.cacheable.CacheableBenchmark;
import com.google.caliper.Runner;
import org.junit.Test;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 2:37 PM
 */
public class TestBenchmark {

	@Test
	public void run() throws Exception {
		Runner.main(CacheableBenchmark.class, new String[]{"--trials", "1"});
	}

}
