package com.fcesur.productservice.mapper;


import com.fcesur.productservice.dto.ProductRequest;
import com.fcesur.productservice.dto.ProductResponse;
import com.fcesur.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product map(ProductRequest request);

    ProductResponse mapToResponse(Product product);

    Product update(@MappingTarget Product product, ProductRequest request);
}
