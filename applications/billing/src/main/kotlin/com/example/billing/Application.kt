package com.example.billing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
@EnableCircuitBreaker

//class Application {
//    companion object {
//        @JvmStatic fun main(args: Array<String>) {
//            SpringApplication.run(Application::class.java, *args)
//        }
//    }
//}

class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
