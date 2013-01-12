package com.blogspot.nurkiewicz.cacheable;

import com.blogspot.nurkiewicz.cacheable.calculator.Calculator;
import com.blogspot.nurkiewicz.cacheable.calculator.ManuallyInstrumentedCalculator;
import com.blogspot.nurkiewicz.cacheable.calculator.PlainCalculator;
import com.blogspot.nurkiewicz.cacheable.calculator.SpringInstrumentedCalculator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tomasz Nurkiewicz
 * @since 1/12/13, 5:28 PM
 */
public abstract class BaseConfig {

	@Bean
	public Calculator calculator() {
		return new PlainCalculator();
	}

}

@Configuration
class NoCachingConfig extends BaseConfig {}

@Configuration
class ManualCachingConfig extends BaseConfig {
	@Bean
	@Override
	public Calculator calculator() {
		return new CachingCalculatorDecorator(super.calculator());
	}
}

@Configuration
abstract class CacheManagerConfig extends BaseConfig {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

}

@Configuration
@EnableCaching(proxyTargetClass = true)
class CacheableCglibConfig extends CacheManagerConfig {}

@Configuration
@EnableCaching(proxyTargetClass = false)
class CacheableJdkProxyConfig extends CacheManagerConfig {}

@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
class CacheableAspectJWeaving extends CacheManagerConfig {

	@Bean
	@Override
	public Calculator calculator() {
		return new SpringInstrumentedCalculator();
	}

}

@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
class AspectJCustomAspect extends CacheManagerConfig {

	@Bean
	@Override
	public Calculator calculator() {
		return new ManuallyInstrumentedCalculator();
	}

}

