package com.example.billing.reocurringPayments;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Service {
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    private static final Random rand = new Random();

    @HystrixCommand(fallbackMethod = "thisMayFailFallback")
    public String thisMayFail() {
        if (rand.nextInt(100) % 10 == 0)
            throw new RuntimeException("BOOM!");
        return "SUCCESS!";
    }

    public String thisMayFailFallback() {
        logger.info("*********************** Calling Fallback... *************************");
        return "FALLBACK!";
    }
}
