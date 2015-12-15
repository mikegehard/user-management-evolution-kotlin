package com.example.billing.reocurringPayments

import com.example.payments.Gateway
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class Controller {
    private val paymentGateway: com.example.payments.Gateway

    private val counter: CounterService

    private val service: Service


    @Inject
    constructor(paymentGateway: Gateway, counterService: CounterService, service: Service) {
        this.paymentGateway = paymentGateway
        this.counter = counterService
        this.service = service
    }

    @RequestMapping(value = "/reocurringPayment", method = arrayOf(RequestMethod.POST))
    fun createReocurringPayment(@RequestBody data: Map<String, Any>): ResponseEntity<String> {
        val responseHeaders = HttpHeaders().apply {
            add("content-type", MediaType.APPLICATION_JSON_VALUE)
        }

        service.thisMayFail()

        val createSuccessful = paymentGateway.createReocurringPayment(data["amount"] as Int)

        val response = if (createSuccessful) {
            counter.increment("billing.reocurringPayment.created")
            ResponseEntity("{\"errors\": []}", responseHeaders, HttpStatus.CREATED)
        } else {
            ResponseEntity("{\"errors\": [\"error1\", \"error2\"]}", responseHeaders, HttpStatus.BAD_REQUEST)
        }

        return response
    }
}
