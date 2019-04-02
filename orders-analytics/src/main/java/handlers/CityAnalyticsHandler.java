package handlers;

import com.google.gson.Gson;
import com.procourier.model.Order;
import com.procourier.model.OrderLine;
import http.OrdersServiceClient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import spark.Request;
import spark.Response;
import spark.Route;

public final class CityAnalyticsHandler implements Route {
    private final OrdersServiceClient client;
    private final Gson gson = new Gson();

    public CityAnalyticsHandler(OrdersServiceClient client) {
        this.client = client;
    }

    @Override
    public Object handle(Request request, Response response) {
        final var cityName = request.params("name");
        response.type("applications/json");

        final Order someOrder = client.getOrder(1L);

        long minSold = Optional.ofNullable(request.queryParams("minSold"))
                .map(Long::valueOf)
                .orElse(0L);

        final List<Order> ordersInCity = client.getAllOrders().stream()
                .filter(order -> order.getBuyer().getAddress().getCity().equals(cityName))
                .collect(Collectors.toList());

        long totalSold = 0L;

        for (final var order : ordersInCity) {
            final long price = order.getOrderLines().stream()
                    .filter(orderLine -> orderLine.getProduct().getPrice() > minSold)
                    .map(orderLine -> orderLine.getQuantity() * orderLine.getProduct().getPrice())
                    .reduce(0L,(leftOperator, rightOperator) -> leftOperator + rightOperator);

            totalSold += price;
        }
        
        long numberOfDifferentBuyers = ordersInCity.stream()
		        .map(order -> order.getBuyer().getId())
                .distinct()
		        .count();
        
        var analyticsResponse =  new AnalyticsResponse(cityName, totalSold, numberOfDifferentBuyers);
        return gson.toJson(analyticsResponse);
    }

    private static final class AnalyticsResponse {
        private final String cityName;
        private final Long totalSold;
        private final Long numberOfDifferentBuyers;

        public AnalyticsResponse(String cityName, Long totalSold, Long numberOfDifferentBuyers) {
            this.cityName = cityName;
            this.totalSold = totalSold;
            this.numberOfDifferentBuyers = numberOfDifferentBuyers;
        }

        public String cityName() {
            return cityName;
        }

        public Long totalSold() {
            return totalSold;
        }

        public Long getNumberOfDifferentBuyers() {
            return numberOfDifferentBuyers;
        }
    }
}
