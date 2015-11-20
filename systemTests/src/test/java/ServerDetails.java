import static com.jayway.restassured.RestAssured.get;

public class ServerDetails{
    private final String serverName;
    private final String metricName;



    public ServerDetails(String serverName, String metricName) {
        this.serverName = serverName;
        this.metricName = metricName;
    }

    private String getServerName() {
        return serverName;
    }

    private String getMetricName() {
        return metricName;
    }

    public String getUrl() {
        return "http://" + getServerName();
    }

    public int getMetricValue() {
        try {
            return get(
                    getUrl() + "/metrics"
            )
                    .then()
                    .extract().body().path(getMetricName());
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
