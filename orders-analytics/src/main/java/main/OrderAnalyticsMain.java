package main;

import handlers.CityAnalyticsHandler;
import http.CacheableOrdersServiceClient;
import http.DefaultOrdersServiceClient;
import http.OrdersServiceClient;
import spark.Spark;

import java.io.IOException;
import java.util.Properties;

public final class OrderAnalyticsMain {
    public static void main(String[] args) throws IOException {
        Spark.port(8084);
        Spark.threadPool(40, 20, 5000);

        final String ordersServiceBaseUri = getOrdersServiceBaseUri();
        final OrdersServiceClient client = new DefaultOrdersServiceClient(ordersServiceBaseUri);
        final OrdersServiceClient cachedServiceClient = new CacheableOrdersServiceClient(client);

        final var handler = new CityAnalyticsHandler(cachedServiceClient);

        Spark.get("/city/:name", handler);
    }

    private static String getOrdersServiceBaseUri() throws IOException {
        final var properties = new Properties();
        properties.load(OrderAnalyticsMain.class.getResourceAsStream("/application.properties"));
        return properties.getOrDefault("order.service.uri", "http://localhost:8080").toString();
    }
}
