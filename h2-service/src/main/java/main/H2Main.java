package main;

import com.procourier.model.Address;
import com.procourier.model.Buyer;
import com.procourier.model.Courier;
import com.procourier.model.Order;
import com.procourier.model.OrderLine;
import com.procourier.model.Product;
import com.procourier.model.Seller;
import service.OrderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.h2.tools.Server.createWebServer;

public final class H2Main {
    public static void main(String[] args) throws SQLException {
        createWebServer("-webPort", "10500").start();

        final OrderService orderService = OrderService.getInstance();
        final var order = createOrder();
        orderService.addOrder(order);

        System.out.println("Order delivered successfully");
    }

    private static Order createOrder() {
        final Product phone = new Product(1000L, "Huawei P30", "New phone", 270L);
        final Product laptop = new Product(1500L, "Sony Vayo", "Old laptop ", 1700L);

        final Address emagAddress =
                new Address("Bucuresti", "Bucuresti", "Soseaua Virtutii", "20A");

        final Address johnAddress = new Address("Dolj", "Craiova", "Ecaterina Teodoroiu", "11");
        final Address proCourierAddress = new Address("Timis", "Timisoara", "AI Cuza", "86");

        final Buyer john = new Buyer("John", johnAddress);
        final Seller emag = new Seller("Emag", emagAddress);

        final OrderLine olPhone = new OrderLine(phone, 10L);
        final OrderLine olLaptop = new OrderLine(laptop, 7L);

        final List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(olPhone);
        orderLines.add(olLaptop);

        final Courier courier =
                new Courier("ProCourier", proCourierAddress, 9L, 13L);

        return new Order(emag, john, courier, orderLines );
    }
}
