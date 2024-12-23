package com.tui.proof.infrastructure.adapter.out.persistence.mapper;

import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderResponse;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.OrderEntity;
import io.swagger.v3.oas.annotations.Parameter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper class responsible for mapping {@link OrderRequest}, {@link OrderModel},  {@link OrderEntity} and  {@link OrderResponse}
 */
@Mapper (componentModel = "spring")
public interface OrderMapper {

    /**
     * Maps method between request and model
     * @param orderRequest {@link OrderRequest} input object
     * @return {@link OrderModel} associated to the input param
     */
    OrderModel createModelFromRequest(OrderRequest orderRequest);

    /**
     * Maps method between model and response
     * @param orderModel {@link OrderModel} input object
     * @return {@link OrderResponse} associated to the input param
     */
    OrderResponse createResponseFromModel(OrderModel orderModel);

    /**
     * Maps method between model and entity
     * @param orderModel {@link OrderModel} input object
     * @return {@link OrderEntity} associated to the input param
     */
    OrderEntity modelToEntity(OrderModel orderModel);

    /**
     * Maps method between entity and model
     * @param orderEntity {@link OrderEntity} input object
     * @return {@link OrderModel} associated to the input param
     */
    @Mapping(source = "id", target = "number")
    OrderModel entityToModel(OrderEntity orderEntity);
}
