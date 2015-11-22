package com.example.billing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
// This class must not be final or Spring Boot is not happy.
open class Application {
    companion object {
        @JvmStatic public fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}

