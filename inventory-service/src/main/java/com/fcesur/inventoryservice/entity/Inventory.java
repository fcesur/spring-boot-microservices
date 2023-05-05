package com.fcesur.inventoryservice.entity;

import com.fcesur.inventoryservice.entity.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "inventory")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity {
    private String skuCode;
    private Integer quantity;
}
