package com.example.subscriptions

import com.example.billing.BillingRequest
import com.example.billing.Service
import com.example.email.EmailMessage
import com.example.email.SendEmail

class CreateSubscription(
        private val billingService: Service,
        private val emailSender: SendEmail, private val subscriptions: SubscriptionRepository) {

    fun run(userId: String, packageId: String) {
        subscriptions.create(Subscription(userId, packageId))
        billingService.billUser(BillingRequest(userId, 100))
        emailSender.run(EmailMessage("me@example.com", "Subscription Created", "Some email body"))
    }
}
