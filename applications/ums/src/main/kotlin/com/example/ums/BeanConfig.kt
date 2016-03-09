package com.example.ums

import com.example.billing.Client
import com.example.billing.Service
import com.example.email.SendEmail
import com.example.subscriptions.SubscriptionRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
open class BeanConfig {

    // In Spring 4.3, you will be able to do constructor injection of these and it will clean
    // up these declarations a bit.
    // https://spring.io/blog/2016/03/04/core-container-refinements-in-spring-framework-4-3
    @Autowired
    lateinit private var datasource: NamedParameterJdbcTemplate

    @Autowired
    lateinit private var billingclient: Client

    @Autowired
    lateinit private var emailConfiguration: EmailConfiguration

    @Autowired
    lateinit private var rabbitTemplate: RabbitTemplate

    @Bean
    open fun subscriptionRepository() = SubscriptionRepository(datasource)

    @Bean
    open fun billingService() = Service(billingclient)

    @Bean
    open fun emailSender() = SendEmail(emailConfiguration.queue, rabbitTemplate)
}
