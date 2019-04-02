package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.procourier.model.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public final class DefaultOrdersServiceClient implements OrdersServiceClient {
    private final Gson gson = new Gson();
    private final HttpClient httpClient;
    private final HttpRequest.Builder builder;
    private final String baseUri;

    public DefaultOrdersServiceClient(String baseUri) {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        this.builder = HttpRequest.newBuilder()
                .GET();

        this.baseUri = baseUri;
    }

    @Override
    public Order getOrder(Long id) {
        final HttpRequest request = this.builder
                .uri(URI.create(this.baseUri + "/orders/" + id))
                .build();

        final var response = tryHttpRequest(request);
        return gson.fromJson(response.body(), Order.class);

    }

    @Override
    public List<Order> getAllOrders() {
        final var request = this.builder
                .uri(URI.create(this.baseUri + "/orders"))
                .build();

        final var response = tryHttpRequest(request);

        return gson.fromJson(response.body(), new TypeToken<List<Order>>(){}.getType());
    }

    private HttpResponse<String> tryHttpRequest(HttpRequest request) {
        try {
            final HttpResponse<String> response =
                    this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not retrieve specified error");
        }
    }
}
