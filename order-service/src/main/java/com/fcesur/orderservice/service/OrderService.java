package com.fcesur.orderservice.service;

import com.fcesur.orderservice.dto.InventoryResponse;
import com.fcesur.orderservice.dto.OrderRequest;
import com.fcesur.orderservice.dto.OrderResponse;
import com.fcesur.orderservice.entity.Order;
import com.fcesur.orderservice.entity.OrderItem;
import com.fcesur.orderservice.mapper.OrderMapper;
import com.fcesur.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient webClient;

    public OrderResponse placeOrder(OrderRequest request) {
        Order order = orderMapper.map(request);
        Order savedOrder;

        order.getItems().forEach(orderItem -> orderItem.setOrder(order));

        order.setOrderNumber(UUID.randomUUID().toString());

        List<String> skuCodes = order.getItems().stream()
                .map(OrderItem::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8186/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);

        if (allProductsInStock) {
            savedOrder = orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock.");
        }

        return orderMapper.mapToDto(savedOrder);
    }

}
