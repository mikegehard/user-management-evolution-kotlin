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
    @Autowired
    lateinit private var datasource: NamedParameterJdbcTemplate

    @Autowired
    lateinit private var billingclient: Client

    @Autowired
    lateinit private var emailConfiguration: EmailConfiguration

    @Autowired
    lateinit private var rabbitTemplate: RabbitTemplate

    @Bean
    open fun subscriptionRepository(): SubscriptionRepository = SubscriptionRepository(datasource)

    @Bean
    open fun billingService(): Service = Service(billingclient)

    @Bean
    open fun emailSender(): SendEmail = SendEmail(emailConfiguration.queue, rabbitTemplate)
}
