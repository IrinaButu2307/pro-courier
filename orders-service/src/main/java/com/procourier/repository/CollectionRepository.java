package com.procourier.repository;

import com.procourier.model.Order;
import com.procourier.model.repository.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionRepository implements OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();


    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Long put(Order item) {
        orders.put(item.getId(), item);
        return item.getId();
    }

    @Override
    public List<Long> putAll(List<Order> items) {
        final var ids = items.stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        final Map<Long, Order> newValues = items.stream()
                .collect(Collectors.toMap(Order::getId, Function.identity()));

        orders.putAll(newValues);

        return ids;
    }
}
