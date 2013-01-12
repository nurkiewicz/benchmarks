package com.blogspot.nurkiewicz.cacheable;

import com.blogspot.nurkiewicz.cacheable.calculator.Calculator;
import com.google.caliper.SimpleBenchmark;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 12:07 AM
 */
public class CacheableBenchmark extends SimpleBenchmark {

	private final Calculator noCaching = fromSpringContext(NoCachingConfig.class);
	private final Calculator manualCaching = fromSpringContext(ManualCachingConfig.class);
	private final Calculator cacheableCglib = fromSpringContext(CacheableCglibConfig.class);
	private final Calculator cacheableJdkProxy = fromSpringContext(CacheableJdkProxyConfig.class);
	private final Calculator cacheableAspectJ = fromSpringContext(CacheableAspectJWeaving.class);
	private final Calculator aspectJCustom = fromSpringContext(AspectJCustomAspect.class);

	private static <T extends BaseConfig> Calculator fromSpringContext(Class<T> config) {
		return new AnnotationConfigApplicationContext(config).getBean(Calculator.class);
	}

	private int benchmarkWith(Calculator calculator, int reps) {
		int accum = 0;
		for (int i = 0; i < reps; ++i) {
			accum += calculator.identity(i % 16);
		}
		return accum;
	}

	public int timeNoCaching(int reps) {
		return benchmarkWith(noCaching, reps);
	}

	public int timeManualCaching(int reps) {
		return benchmarkWith(manualCaching, reps);
	}

	public int timeCacheableWithCglib(int reps) {
		return benchmarkWith(cacheableCglib, reps);
	}

	public int timeCacheableWithJdkProxy(int reps) {
		return benchmarkWith(cacheableJdkProxy, reps);
	}

	public int timeCacheableWithAspectJWeaving(int reps) {
		return benchmarkWith(cacheableAspectJ, reps);
	}

	public int timeAspectJCustom(int reps) {
		return benchmarkWith(aspectJCustom, reps);
	}
}
