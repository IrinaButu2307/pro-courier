package com.procourier.handlers;

import com.google.gson.Gson;
import com.procourier.model.Order;
import service.OrderService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public final class OrdersHandler implements Route {
    private final OrderService orderService;
    private final Gson gson = new Gson();

    public OrdersHandler(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.header("Content-Type", "application/json;charset=utf-8");
        final List<Order> orders = orderService.getOrders();
        return gson.toJson(orders);
    }


}
