package com.blogspot.nurkiewicz.cacheable

import calculator.{ManuallyInstrumentedCalculator, SpringInstrumentedCalculator, Calculator, PlainCalculator}
import org.springframework.context.annotation.{Primary, AdviceMode, Bean, Configuration}
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.cache.support.NoOpCacheManager

abstract class BaseConfig {

	@Bean
	def calculator(): Calculator = new PlainCalculator()

}

@Configuration
class NoCachingConfig extends BaseConfig

@Configuration
class ManualCachingConfig extends BaseConfig {
	@Bean
	override def calculator() = new CachingCalculatorDecorator(super.calculator())

}

@Configuration
abstract class CacheManagerConfig extends BaseConfig {

	@Bean
	def cacheManager(): CacheManager = new ConcurrentMapCacheManager()

}

@Configuration
@EnableCaching(proxyTargetClass = true)
class CacheableCglibConfig extends CacheManagerConfig

@Configuration
@EnableCaching(proxyTargetClass = false)
class CacheableJdkProxyConfig extends CacheManagerConfig

@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
class CacheableAspectJWeaving extends CacheManagerConfig {

	@Bean
	override def calculator() = new SpringInstrumentedCalculator

}

@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
class AspectJCustomAspect extends CacheManagerConfig {

	@Bean
	override def calculator() = new ManuallyInstrumentedCalculator

}
