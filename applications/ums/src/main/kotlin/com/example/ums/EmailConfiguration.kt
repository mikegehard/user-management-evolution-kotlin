package com.example.ums

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("email")
open class EmailConfiguration(var queue: String = "")
