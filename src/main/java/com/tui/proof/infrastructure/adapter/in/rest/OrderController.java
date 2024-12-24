package com.tui.proof.infrastructure.adapter.in.rest;

import com.tui.proof.application.port.in.OrderPort;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderResponse;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> searchOrdersByClientData(
            @Param("clientContains") @NonNull String clientContains) {
        List<OrderModel> orderModels = orderPort.searchOrdersByClient(clientContains);
        List<OrderResponse> orderResponses =
                orderModels.stream().map(orderMapper::createResponseFromModel).toList();
        return ResponseEntity.ok(orderResponses);
    }
}
