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
	public void runCacheableBenchmark() throws Exception {
		Runner.main(CacheableBenchmark.class, new String[]{"--trials", "1"});
	}

	@Test
	public void runInliningBenchmark() throws Exception {
		Runner.main(InliningBenchmark.class, new String[]{"--trials", "1"});
	}

}
