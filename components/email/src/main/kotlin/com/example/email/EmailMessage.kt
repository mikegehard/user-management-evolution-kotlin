package com.example.email

import java.io.Serializable

data class EmailMessage(val toAddress: String, val subject: String, val body: String) : Serializable
