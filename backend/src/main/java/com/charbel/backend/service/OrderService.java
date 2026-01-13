package com.charbel.backend.service;

import org.springframework.stereotype.Service;

import com.charbel.backend.entity.Order;
import com.charbel.backend.repository.OrderRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
