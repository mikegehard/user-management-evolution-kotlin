import static com.jayway.restassured.RestAssured.get;

public class ServerDetails{
    private final String serverName;
    private final String serverUrl;
    private final String metricName;



    public ServerDetails(String serverName, String serverUrl, String metricName) {
        this.serverName = serverName;
        this.serverUrl = serverUrl;
        this.metricName = metricName;
    }


    public String getUrl() {
        return "http://" + serverUrl;
    }

    public int getMetricValue() {
        try {
            return get(
                    getUrl() + "/metrics"
            )
                    .then()
                    .extract().body().path(metricName);
        } catch (NullPointerException e) {
            return 0;
        } catch (Exception e) {
            System.err.println("Error getting metrics for: " + serverName);
            System.err.println(e.getMessage());
            return 0;
        }
    }
}
