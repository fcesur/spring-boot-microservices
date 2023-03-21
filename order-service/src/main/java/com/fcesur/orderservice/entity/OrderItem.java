package com.fcesur.orderservice.entity;


import com.fcesur.orderservice.entity.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem extends BaseEntity {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    @ManyToOne
    private Order order;

}
