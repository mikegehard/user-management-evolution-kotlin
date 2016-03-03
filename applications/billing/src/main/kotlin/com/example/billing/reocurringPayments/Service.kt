package com.example.billing.reocurringPayments

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import java.util.*

open class Service {
    @HystrixCommand(fallbackMethod = "thisMayFailFallback")
    // This has to be open or the method will no be
    // overridden and the exception will not get caught when it happens.
    open fun thisMayFail(): String {
        if (rand.nextInt(100) % 10 == 0)
            throw RuntimeException("BOOM!")
        return "SUCCESS!"
    }

    fun thisMayFailFallback(): String {
        logger.info("*********************** Calling Fallback... *************************")
        return "FALLBACK!"
    }

    // Putting these in the companion object is like having them
    // as static in Java.
    companion object {
        private val logger = LoggerFactory.getLogger(Service::class.java)
        private val rand = Random()
    }
}
