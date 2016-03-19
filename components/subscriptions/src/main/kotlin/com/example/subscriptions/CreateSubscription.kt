package com.example.subscriptions

import com.example.billing.BillingRequest
import com.example.billing.Service
import com.example.email.EmailMessage
import com.example.email.SendEmail

sealed class CreateSubscriptionResult {
    class Success(val subscription: Subscription): CreateSubscriptionResult()
    class Failure(val errors: List<String>): CreateSubscriptionResult()
}

class CreateSubscription(
        private val billingService: Service,
        private val emailSender: SendEmail, private val subscriptions: SubscriptionRepository) {

    fun run(userId: String, packageId: String): CreateSubscriptionResult {
        val subscription = Subscription(userId = userId, packageId = packageId)
        subscriptions.create(subscription)
        billingService.billUser(BillingRequest(userId, 100))
        emailSender.run(EmailMessage("me@example.com", "Subscription Created", "Some email body"))

        return if (userId.equals(-1)) {
            CreateSubscriptionResult.Failure(listOf("Some bad thing happened!!"))
        } else {
            CreateSubscriptionResult.Success(subscription)
        }
    }
}
