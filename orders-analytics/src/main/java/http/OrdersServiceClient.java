package http;

import com.procourier.model.Order;

import java.util.List;

public interface OrdersServiceClient {
    Order getOrder(Long id);

    List<Order> getAllOrders();
}
