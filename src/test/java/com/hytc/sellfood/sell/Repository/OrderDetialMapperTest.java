package com.hytc.sellfood.sell.Repository;

import com.hytc.sellfood.sell.ObjectMapper.OrderDetial;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderDetialMapperTest {

    @Autowired
    private OrderDetialMapper orderDetialMapper;

    @Test
    public void saveTest() {
        OrderDetial orderDetial = new OrderDetial();
        orderDetial.setDetialId("1");
        orderDetial.setOrderId("1");
        orderDetial.setProductIcon("/src/sad/sad");
        orderDetial.setProductName("牛奶");
        orderDetial.setProductId("1");
        orderDetial.setProductQuantity(12);
        orderDetial.setProductSinglePrice(new BigDecimal("12.1"));
        orderDetial.setProductSummaryPrice(new BigDecimal("20.11"));
        OrderDetial orderDetialSave = orderDetialMapper.save(orderDetial);
        Assert.assertNotNull(orderDetialSave);
    }

    @Test
    public void findByOrderIdTest() {
        List<OrderDetial> list = orderDetialMapper.findByOrderId("1");
        Assert.assertNotNull(list);
    }
}