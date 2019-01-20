package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class ProductCategoery implements Serializable  {

    private static final long serialVersionUID = -7321789407128200800L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoeryId;

    private String categoeryName;

    private Integer categoeryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategoery() {
    }

    public ProductCategoery(String categoeryName, Integer categoeryType) {
        this.categoeryName = categoeryName;
        this.categoeryType = categoeryType;
    }
}
