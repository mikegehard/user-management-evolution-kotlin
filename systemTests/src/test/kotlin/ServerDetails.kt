import com.jayway.restassured.RestAssured.get

class ServerDetails(private val serverName: String, private val serverUrl: String, private val metricName: String) {
    val url: String
        get() = "http://" + serverUrl

    val metricValue: Int
        get() {
            try {
                return get(
                        url + "/metrics").then().extract().body().path<Int>(metricName)
            } catch (e: NullPointerException) {
                return 0
            } catch (e: Exception) {
                System.err.println("Error getting metrics for: " + serverName)
                System.err.println(e.message)
                return 0
            }

        }
}
