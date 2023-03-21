package com.fcesur.orderservice.service;


import com.fcesur.orderservice.dto.OrderRequest;
import com.fcesur.orderservice.dto.OrderResponse;
import com.fcesur.orderservice.entity.Order;
import com.fcesur.orderservice.mapper.OrderMapper;
import com.fcesur.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse placeOrder(OrderRequest request) {
        Order order = orderMapper.map(request);
        order.setOrderNumber(UUID.randomUUID().toString());

        Order savedOrder = orderRepository.save(order);

        return orderMapper.mapToDto(savedOrder);
    }

}
