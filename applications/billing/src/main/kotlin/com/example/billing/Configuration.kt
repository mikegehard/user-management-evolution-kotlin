package com.example.billing

import com.example.billing.reocurringPayments.Service
import com.example.payments.Gateway
import com.example.payments.RecurlyGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
// This class must not be final (final is the default in Kotlin)
// or Spring Boot is not happy.
open class Configuration {
    @Bean
    // This method must be overrideable (not overrideable is the default in Kotlin)
    // or Spring Boot is not happy.
    open fun paymentGateway(): Gateway = RecurlyGateway()

    @Bean
    // This method must be overrideable (not overrideable is the default in Kotlin)
    // or Spring Boot is not happy.
    open fun serviceThatMayFail(): Service = Service()
}
