import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.hamcrest.Matchers.equalTo;

public class SystemTest {
    static ServerRegistry registry = new ServerRegistry();

    @Test
    public void billingService() throws InterruptedException, IOException {
        ServerDetails billingServer = registry.get(ServerRegistry.BILLING);
        int billingInitialValue = billingServer.getMetricValue();

        given()
                .contentType("application/json;charset=UTF-8")
                .body("{\"amount\": 100}")
                .when()
                .post(billingServer.getUrl() + "/reocurringPayment")
                .then()
                .statusCode(201);

        assertThat(billingServer.getMetricValue()).isEqualTo(billingInitialValue + 1);
    }

    @Test
    public void endToEnd() throws InterruptedException, IOException {
        List<String> apps = new ArrayList<>();
        apps.add(ServerRegistry.BILLING);
        apps.add(ServerRegistry.EMAIL);
        apps.add(ServerRegistry.UMS);

        Map<String, Integer> initialValues = new HashMap<>();
        for(String app : apps) {
            initialValues.put(app, registry.get(app).getMetricValue());
        }

        given()
                .contentType("application/json;charset=UTF-8")
                .body("{\"userId\": \"abc123\", \"packageId\": \"package123\"}")
                .when()
                .post(registry.get(ServerRegistry.UMS).getUrl() + "/subscriptions")
                .then()
                .statusCode(201)
                .body("acknowledged", equalTo(true));

        Map<String, Integer> actual = new HashMap<>();
        for(String app : apps) {
            actual.put(app, registry.get(app).getMetricValue());
        }

        MapEntry[] expected = new MapEntry[apps.size()];
        int i = 0;
        for(String app : apps) {
            expected[i] = entry(app, initialValues.get(app) + 1);
            i++;
        }

        assertThat(actual).containsOnly(expected);
    }
}
