package com.blogspot.nurkiewicz.cacheable;

import com.blogspot.nurkiewicz.cacheable.calculator.Calculator;
import com.google.caliper.Benchmark;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 12:07 AM
 */
public class CacheableBenchmark extends Benchmark {

	private final Calculator noCaching = fromSpringContext(NoCachingConfig.class);

	private final Calculator manualConcurrentHashMap = fromSpringContext(ManualCachingWithConcurrentHashMapConfig.class);
	private final Calculator manualCacheManager = fromSpringContext(ManualCachingWithCacheManagerConfig.class);
	private final Calculator aspectJConcurrentHashMap = fromSpringContext(AspectJCustomAspect.class);

	private final Calculator cacheableCglib = fromSpringContext(CacheableCglibConfig.class);
	private final Calculator cacheableJdkProxy = fromSpringContext(CacheableJdkProxyConfig.class);
	private final Calculator cacheableAspectJ = fromSpringContext(CacheableAspectJWeaving.class);

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

	public int timeManualWithConcurrentHashMap(int reps) {
		return benchmarkWith(manualConcurrentHashMap, reps);
	}

	public int timeManualWithCacheManager(int reps) {
		return benchmarkWith(manualCacheManager, reps);
	}

	public int timeAspectJWithConcurrentHashMap(int reps) {
		return benchmarkWith(aspectJConcurrentHashMap, reps);
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
}
