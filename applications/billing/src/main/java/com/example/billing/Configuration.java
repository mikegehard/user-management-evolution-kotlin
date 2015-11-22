package com.example.billing;

import com.example.billing.reocurringPayments.Service;
import com.example.payments.Gateway;
import com.example.payments.RecurlyGateway;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    // Wire up the specific implementation of the payment gateway. Because this is an
    // interface, the rest of the code doesn't know anything about the concrete implementation.
    // This implementation can be switched out easily by the Spring IoC framework for testing so
    // that tests do not hit the actual gateway API.
    @Bean
    public Gateway paymentGateway() {
        return new RecurlyGateway();
    }

    // You need a bean for this service so that Spring Boot will automatically connect
    // the bean to Hystrix.
    // See http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html#_circuit_breaker_hystrix_clients.
    @Bean
    public Service serviceThatMayFail() {
        return new Service();
    }

    // An example of pubishing metrics to a writer. This writer can be Redis, OpenTSDB, StatsD
    // or a custom writer.

    // The Metrics Writer stuff is only available in Spring Boot 1.3.0 and Spring Cloud Services
    // currently forces 1.2.4.
    //    @Bean
    //    @ExportMetricWriter
    //    MetricWriter metricWriter(@Qualifier("mbeanExporter") MBeanExporter exporter) {
    //        return new JmxMetricWriter(exporter);
    //    }
}
