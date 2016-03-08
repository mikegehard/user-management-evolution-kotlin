package com.example.billing

import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("http://billing")
interface Client {
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/reocurringPayment", consumes = arrayOf("application/json"))
    fun billUser(request: BillingRequest)
}
