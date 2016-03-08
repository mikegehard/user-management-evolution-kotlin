package com.example.email

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class EmailConfiguration {
    @Value("\${queueName}")
    lateinit var queueName: String

    @Autowired
    lateinit var counter: CounterService

    @Bean
    open fun exchange() = TopicExchange("spring-boot-exchange")

    @Bean
    open fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.messageListener = listenerAdapter
        return container
    }

    @Bean
    open fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(queueName)
    }

    @Bean
    open fun receiver() = EmailMessageReceiver(counter)

    @Bean
    open fun listenerAdapter(receiver: EmailMessageReceiver) = MessageListenerAdapter(receiver, "process")

    @Bean
    open fun queue() = Queue(queueName, false)
}
