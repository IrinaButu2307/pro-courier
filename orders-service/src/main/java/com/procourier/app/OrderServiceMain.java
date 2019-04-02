package com.procourier.app;

import com.procourier.handlers.OrderByIdHandler;
import com.procourier.handlers.OrdersHandler;
import main.H2Main;
import service.OrderService;
import spark.Spark;

public final class OrderServiceMain {

    public static void main(String[] args) throws Exception {
        H2Main.main(new String[]{});
        final var orderByIdHandler = new OrderByIdHandler(OrderService.getInstance());
        final var ordersHandler = new OrdersHandler(OrderService.getInstance());

        Spark.port(8082);
        Spark.threadPool(26, 10, 2000);
        Spark.get("/orders/:id", orderByIdHandler);
        Spark.get("/orders", ordersHandler);
    }
}
