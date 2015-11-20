package com.example.billing.reocurringPayments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private com.example.payments.Gateway paymentGateway;

    @Autowired
    private CounterService counter;

    @Autowired
    Service service;

    @RequestMapping(value = "/reocurringPayment", method = RequestMethod.POST)
    public ResponseEntity<String> createReocurringPayment(@RequestBody Map<String, Object> data){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_JSON.toString());

        // make sure that Hystrix is involved in a silly way so it shows up on the dashboard. :-)
        service.thisMayFail();

        ResponseEntity<String> response;
        if (paymentGateway.createReocurringPayment((Integer)data.get("amount"))) {
            // This can be found in the JMX console (JConsole comes with JDK) as an MBean
            // (org.springframework.metrics.counter.billing.reocurringPayment.created)
            counter.increment("billing.reocurringPayment.created");
            response = new ResponseEntity<>("{\"errors\": []}", responseHeaders, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("{\"errors\": [\"error1\", \"error2\"]}", responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
