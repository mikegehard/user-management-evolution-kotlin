package com.example.ums.subscriptions

import com.example.billing.Service
import com.example.email.SendEmail
import com.example.subscriptions.CreateSubscription
import com.example.subscriptions.Subscription
import com.example.subscriptions.SubscriptionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/subscriptions")
class SubscriptionsController @Autowired constructor(val subscriptions: SubscriptionRepository, val billingService: Service, val emailSender: SendEmail, val counter: CounterService) {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun index(): Iterable<Subscription> {
        return subscriptions.all()
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody params: Map<String, String>): ResponseEntity<String> {
        val responseHeaders = HttpHeaders().apply {
            add("content-type", MediaType.APPLICATION_JSON.toString())
        }

        CreateSubscription(billingService, emailSender, subscriptions).run(params["userId"], params["packageId"])

        counter.increment("ums.subscription.created")

        return ResponseEntity("{ \"acknowledged\": true }", responseHeaders, HttpStatus.CREATED)
    }
}
