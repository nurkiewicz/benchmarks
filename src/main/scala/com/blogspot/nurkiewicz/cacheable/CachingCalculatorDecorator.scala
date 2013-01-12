package com.blogspot.nurkiewicz.cacheable

import calculator.Calculator
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Configurable
import collection.JavaConversions._


class CachingCalculatorDecorator(target: Calculator) extends Calculator {
	private val cache = new java.util.concurrent.ConcurrentHashMap[java.lang.Integer, java.lang.Integer]

	override def identity(x: Int) = cache.get(x) match {
		case null =>
			val v = target.identity(x)
			cache.put(x, v)
			v
		case v => v
	}
}