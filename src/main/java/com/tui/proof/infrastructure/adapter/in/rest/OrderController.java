package com.tui.proof.infrastructure.adapter.in.rest;

import com.tui.proof.application.port.in.OrderPort;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderResponse;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderPort orderPort;
    private final OrderMapper orderMapper;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {
        OrderModel orderModel = orderMapper.createModelFromRequest(orderRequest);
        OrderModel orderCreated = orderPort.createOrder(orderModel);

        return ResponseEntity.ok(orderMapper.createResponseFromModel(orderCreated));
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> updateOrder(
            @Valid @RequestBody OrderRequest orderRequest) {
        OrderModel orderModel = orderMapper.createModelFromRequest(orderRequest);
        OrderModel orderCreated = orderPort.updateOrder(orderModel);

        return ResponseEntity.ok(orderMapper.createResponseFromModel(orderCreated));
    }
}
