package com.example.ums

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.jdbc.core.JdbcTemplate

@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
@EnableFeignClients(basePackages = arrayOf("com.example.billing"))
@EnableCircuitBreaker

class Application : CommandLineRunner{
    private val logger = LoggerFactory.getLogger(Application::class.java)

    @Autowired
    lateinit private var jdbcTemplate: JdbcTemplate

    @Throws(Exception::class)
    override fun run(vararg strings: String) {
        logger.info("********Setting up database********")
        jdbcTemplate.execute("DROP TABLE subscriptions IF EXISTS")
        jdbcTemplate.execute("CREATE TABLE subscriptions(" + "id SERIAL, userId VARCHAR(255), packageId VARCHAR(255))")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

