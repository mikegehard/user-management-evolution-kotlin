package com.example.email


import org.springframework.amqp.rabbit.core.RabbitTemplate

class SendEmail(private val queueName: String, private val rabbitTemplate: RabbitTemplate) {

    fun run(message: EmailMessage) {
        rabbitTemplate.convertAndSend(queueName, message)
    }
}
