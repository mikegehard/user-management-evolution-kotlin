import com.jayway.restassured.RestAssured.given
import io.damo.kspec.Spec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.MapEntry.entry
import org.hamcrest.Matchers.equalTo

class SystemTest : Spec({
    val registry = ServerRegistry()

    // can the describe be optional in this case where all I want is tests??
    describe("testing the billing service") {
        test {
            val billingServer = registry.get(ServerRegistry.BILLING)
            val billingInitialValue = billingServer.metricValue

            given()
                    .contentType("application/json;charset=UTF-8")
                    .body("{\"amount\": 100}")
                    // when is a keyword in Kotlin so you need the backtick
                    .`when`()
                    .post(billingServer.url + "/reocurringPayment")
                    .then()
                    .statusCode(201)

            assertThat(billingServer.metricValue).isEqualTo(billingInitialValue + 1)
        }
    }

    describe("testing the whole system") {
        test {
            val apps = listOf(ServerRegistry.BILLING, ServerRegistry.EMAIL, ServerRegistry.UMS)

            val initialValues = apps.fold(hashMapOf<String, Int>()) { acc, app -> acc.put(app, registry.get(app).metricValue); acc }

            given()
                    .contentType("application/json;charset=UTF-8")
                    .body("{\"userId\": \"abc123\", \"packageId\": \"package123\"}")
                    // when is a keyword in Kotlin so you need the backtick
                    .`when`()
                    .post(registry.get(ServerRegistry.UMS).url + "/subscriptions")
                    .then()
                    .statusCode(201)
                    .body("acknowledged", equalTo(true));

            val actual = apps.map { app -> entry(app, registry.get(app).metricValue) }

            val expected = apps.map { app -> entry(app, initialValues.getOrElse(app) { 0 } + 1) }.toTypedArray()

            assertThat(actual).containsOnly(*expected)
        }
    }
})
