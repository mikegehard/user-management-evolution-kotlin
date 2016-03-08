package com.example.billing

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory


// Currently you can not attach Hystrix fallbacks to Feign clients
// so you need to wrap your client in a service to be able to
// attach fallbacks.
open class Service(val client: Client) {

    @HystrixCommand(fallbackMethod = "billUserFallback")
    open fun billUser(billingRequest: BillingRequest) {
        client.billUser(billingRequest)
    }

    fun billUserFallback(billingRequest: BillingRequest) {
        // Here you could do anything you wanted as a fallback.
        logger.info("Calling Fallback..." + billingRequest)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Service::class.java)
    }
}
