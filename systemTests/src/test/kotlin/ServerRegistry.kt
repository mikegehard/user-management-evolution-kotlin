import java.util.*

class ServerRegistry {

    init {
        servers.put(BILLING, billingServer)
        servers.put(EMAIL, ServerDetails(EMAIL, "localhost:8080", "'counter.emails.sent'"))
        servers.put(UMS, ServerDetails(UMS, "localhost:8082", "'counter.ums.subscription.created'"))

    }

    operator fun get(server: String): ServerDetails {
        return servers.getOrDefault(server, billingServer)
    }

    companion object {
        val BILLING = "billing-test"
        val UMS = "user-management-test"
        val EMAIL = "email-test"

        private val billingServer = ServerDetails(BILLING, "localhost:8081", "'counter.billing.reocurringPayment.created'")

        private val servers = HashMap<String, ServerDetails>()
    }
}
