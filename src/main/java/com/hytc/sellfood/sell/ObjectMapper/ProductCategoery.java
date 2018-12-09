package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicUpdate
@Data
public class ProductCategoery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoeryId;

    private String categoeryName;

    private Integer categoeryType;

    public ProductCategoery() {
    }

    public ProductCategoery(String categoeryName, Integer categoeryType) {
        this.categoeryName = categoeryName;
        this.categoeryType = categoeryType;
    }
}
