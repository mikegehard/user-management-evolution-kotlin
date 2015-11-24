package com.example.billing.reocurringPayments

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
class Controller {
    @Autowired
    private lateinit var paymentGateway: com.example.payments.Gateway

    @Autowired
    private lateinit var counter: CounterService

    @Autowired
    private lateinit var service: Service

    @RequestMapping(value = "/reocurringPayment", method = arrayOf(RequestMethod.POST))
    fun createReocurringPayment(@RequestBody data: Map<String, Any>): ResponseEntity<String> {
        val responseHeaders = HttpHeaders()
        responseHeaders.add("content-type", MediaType.APPLICATION_JSON.toString())

        service.thisMayFail()

        val response: ResponseEntity<String>
        if (paymentGateway.createReocurringPayment(data["amount"] as Int)) {
            counter.increment("billing.reocurringPayment.created")
            response = ResponseEntity("{\"errors\": []}", responseHeaders, HttpStatus.CREATED)
        } else {
            response = ResponseEntity("{\"errors\": [\"error1\", \"error2\"]}", responseHeaders, HttpStatus.BAD_REQUEST)
        }

        return response
    }
}
