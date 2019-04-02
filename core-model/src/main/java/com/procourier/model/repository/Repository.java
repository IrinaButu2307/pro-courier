package com.procourier.model.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> findById(Long id);
    List<T> getAll();
    Long put(T item);
    List<Long> putAll(List<T> items);
}
