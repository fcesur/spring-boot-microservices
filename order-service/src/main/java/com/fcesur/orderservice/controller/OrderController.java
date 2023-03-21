package com.fcesur.orderservice.controller;


import com.fcesur.orderservice.dto.OrderRequest;
import com.fcesur.orderservice.dto.OrderResponse;
import com.fcesur.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

}
