package com.example.subscriptions

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
// These are mutable references here...
// Is there a better way to do this such that the
// Subscription is immutable???
data class Subscription(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Long = 0,
        var userId: String = "",
        var packageId: String = ""
)
