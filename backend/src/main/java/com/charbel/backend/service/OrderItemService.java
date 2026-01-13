package com.charbel.backend.service;

import org.springframework.stereotype.Service;

import com.charbel.backend.entity.OrderItem;
import com.charbel.backend.repository.OrderItemRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem saveOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id){
        orderItemRepository.deleteById(id);
    }
}
