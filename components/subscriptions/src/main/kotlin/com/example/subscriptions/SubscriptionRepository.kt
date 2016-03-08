package com.example.subscriptions


import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.HashMap

class SubscriptionRepository(private val datasource: NamedParameterJdbcTemplate) {

    fun all(): List<Subscription> {
        return datasource.query("SELECT * FROM subscriptions;") { record, rowNumber -> Subscription(record.getLong("id"), record.getString("userId"), record.getString("packageId")) }
    }

    fun create(subscription: Subscription) {
        val values = HashMap<String, Any>()
        values.put("userId", subscription.userId)
        values.put("packageId", subscription.packageId)

        datasource.update("INSERT INTO subscriptions (userId, packageId) VALUES (:userId, :packageId);", values)
    }
}
