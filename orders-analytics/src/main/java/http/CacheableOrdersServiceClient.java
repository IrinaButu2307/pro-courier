package http;

import com.procourier.model.Order;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CacheableOrdersServiceClient implements OrdersServiceClient {
    private final OrdersServiceClient client;

    private final Map<Long, Order> cacheOrdersMap = new HashMap<>();

    public CacheableOrdersServiceClient(OrdersServiceClient client) {
        final EvictCacheHandler evictCacheHandler = new EvictCacheHandler(cacheOrdersMap);
        Spark.delete("/evict-cache", evictCacheHandler);

        this.client = client;
    }

    @Override
    public Order getOrder(Long id) {
        if (cacheOrdersMap.containsKey(id)) {
            System.out.println("Retrieving id from map");
            return cacheOrdersMap.get(id);
        } else {
            System.out.println("Making Http Request");
            final Order order = client.getOrder(id);
            cacheOrdersMap.put(order.getId(), order);
            return order;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return client.getAllOrders();
    }

    private static final class EvictCacheHandler implements Route {
        private final Map<Long, Order> cachedMap;

        public EvictCacheHandler(Map<Long, Order> cachedMap) {
            this.cachedMap = cachedMap;
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            this.cachedMap.clear();
            return "EVICTED";
        }
    }
}
