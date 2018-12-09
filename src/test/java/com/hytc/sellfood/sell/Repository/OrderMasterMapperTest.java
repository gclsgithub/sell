package com.hytc.sellfood.sell.Repository;

import com.hytc.sellfood.sell.ObjectMapper.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderMasterMapperTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    private static  final String OPENID = "6666";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("江苏无锡");
        orderMaster.setBuyerName("HYTC");
        orderMaster.setBuyerOpenId(OPENID);
        orderMaster.setBuyerTel("12312312312");
        orderMaster.setOrderAmount(new BigDecimal("12.12"));
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        orderMaster.setUpdateTime(LocalDateTime.now());
        orderMaster.setOrderId("1231231");
        OrderMaster savedOderMaster =  orderMasterMapper.save(orderMaster);
        Assert.assertNotNull(savedOderMaster);
    }

    @Test
    public void findByBuyerOpenId() {

        PageRequest pageRequest = new PageRequest(1,3);
        Page<OrderMaster> orderMasters = orderMasterMapper.findByBuyerOpenId(OPENID,pageRequest);

        Assert.assertNotNull(orderMasters.getContent());

    }


}