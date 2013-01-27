package com.blogspot.nurkiewicz;

import com.blogspot.nurkiewicz.cacheable.CacheableBenchmark;
import com.blogspot.nurkiewicz.inlining.InliningBenchmark;
import com.google.caliper.Runner;
import org.junit.Test;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 2:37 PM
 */
public class TestBenchmark {

	@Test
	public void runCacheable() throws Exception {
		new Runner().run("--trials", "1", CacheableBenchmark.class.getName());
	}

	@Test
	public void runInlining() throws Exception {
		new Runner().run("--trials", "1", InliningBenchmark.class.getName());
	}

}
