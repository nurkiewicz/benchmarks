package com.blogspot.nurkiewicz;

import com.blogspot.nurkiewicz.cacheable.CacheableBenchmark;
import com.blogspot.nurkiewicz.inlining.InliningBenchmark;
import com.google.caliper.runner.CaliperMain;
import org.junit.Test;

import java.io.PrintWriter;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 2:37 PM
 */
public class TestBenchmark {

	@Test
	public void runCacheable() throws Exception {
        CaliperMain.exitlessMain(new String[]{"--trials", "1", CacheableBenchmark.class.getName()}, new PrintWriter(System.out, true), new PrintWriter(System.err, true));
	}

	@Test
	public void runInlining() throws Exception {
        CaliperMain.exitlessMain(new String[]{"--trials", "1", InliningBenchmark.class.getName()}, new PrintWriter(System.out, true), new PrintWriter(System.err, true));
	}

}
