package com.fcesur.orderservice.mapper;


import com.fcesur.orderservice.dto.OrderItemDto;
import com.fcesur.orderservice.dto.OrderRequest;
import com.fcesur.orderservice.dto.OrderResponse;
import com.fcesur.orderservice.entity.Order;
import com.fcesur.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order map(OrderRequest request);

    OrderResponse mapToDto(Order entity);

    @Mapping(source = "dto.id", target = "id")
    OrderItem orderItemDtoToEntity(OrderItemDto dto);

    @Mapping(source = "entity.id", target = "id")
    OrderItemDto orderItemToDto(OrderItem entity);

}
