package com.procourier.handlers;

import com.google.gson.Gson;
import com.procourier.model.Order;
import service.OrderService;
import spark.Request;
import spark.Response;
import spark.Route;

import static java.lang.Long.valueOf;

public final class OrderByIdHandler implements Route {
    private final OrderService orderService;
    private final Gson gson = new Gson();

    public OrderByIdHandler(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.header("Content-Type", "application/json;charset=utf-8");
        String id = request.params(":id");
        Order order = orderService.getOrderById(valueOf(id));
        return gson.toJson(order);
    }
}
