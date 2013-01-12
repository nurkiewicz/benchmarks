package com.blogspot.nurkiewicz.cacheable

import calculator.Calculator
import com.google.caliper.SimpleBenchmark
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 12:07 AM
 */
class CacheableBenchmark extends SimpleBenchmark {

	val noCaching: Calculator = fromSpringContext(classOf[NoCachingConfig])
	val manualCaching: Calculator = fromSpringContext(classOf[ManualCachingConfig])
	val cacheableCglib: Calculator = fromSpringContext(classOf[CacheableCglibConfig])
	val cacheableJdkProxy: Calculator = fromSpringContext(classOf[CacheableJdkProxyConfig])
	val cacheableAspectJ: Calculator = fromSpringContext(classOf[CacheableAspectJWeaving])
	val aspectJCustom: Calculator = fromSpringContext(classOf[AspectJCustomAspect])

	println("=" * 80)

	def fromSpringContext[T <: BaseConfig](config: Class[T]) =
		new AnnotationConfigApplicationContext(config).getBean(classOf[Calculator])

	def benchmarkWith(calculator: Calculator, reps: Int) = {
		var i = reps
		var accum = 0L
		while(i > 0) {
			accum += calculator.identity(i % 10)
			i -= 1
		}
		accum
	}

	def timeNoCaching(reps: Int) = benchmarkWith(noCaching, reps)
	def timeManualCaching(reps: Int) = benchmarkWith(manualCaching, reps)
	def timeCacheableWithCglib(reps: Int) = benchmarkWith(cacheableCglib, reps)
	def timeCacheableWithJdkProxy(reps: Int) = benchmarkWith(cacheableJdkProxy, reps)
	def timeCacheableWithAspectJWeaving(reps: Int) = benchmarkWith(cacheableAspectJ, reps)
	def timeAspectJCustom(reps: Int) = benchmarkWith(aspectJCustom, reps)
}
