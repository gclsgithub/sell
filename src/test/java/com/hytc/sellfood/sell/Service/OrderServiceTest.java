package com.hytc.sellfood.sell.Service;

import com.hytc.sellfood.sell.Dto.OrderDto;
import com.hytc.sellfood.sell.ObjectMapper.OrderDetial;
import com.hytc.sellfood.sell.enums.OrderDetailEnum;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceTest {

    private static final String OPEN_ID = "66666666";

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress("江苏");
        orderDto.setBuyerName("小王八");
        orderDto.setBuyerOpenId(OPEN_ID);
        orderDto.setBuyerTel("12345678901");
        List<OrderDetial> orderDetialList = new ArrayList<>();
        OrderDetial orderDetial1 = new OrderDetial();
        orderDetial1.setProductId("1");
        orderDetial1.setProductQuantity(10);
        OrderDetial orderDetial2 = new OrderDetial();
        orderDetial2.setProductId("2");
        orderDetial2.setProductQuantity(14);

        orderDetialList.add(orderDetial2);
        orderDetialList.add(orderDetial1);
        orderDto.setOrderDetialList(orderDetialList);

        orderService.create(orderDto);
    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("1544453450028");
        Assert.assertNotNull(orderDto.getOrderDetialList());
        Assert.assertNotNull(orderDto);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderDto> orderDtos = orderService.findList("666666661", pageRequest);
        Assert.assertNotNull(orderDtos);
    }

    @Test
    public void cancle() {
        OrderDto orderDto = orderService.findOne("1544453450028");
        OrderDto dto = orderService.cancle(orderDto);
        Assert.assertEquals(dto.getOrderStatus(), OrderDetailEnum.CANCLE.getCode());

    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("1544453450028");
        orderDto = orderService.finish(orderDto);
        Assert.assertEquals(orderDto.getOrderStatus(), OrderDetailEnum.IS_COMPLETE.getCode());
    }

    @Test
    public void pay() {
        OrderDto orderDto = orderService.findOne("1544453450028");
        orderDto = orderService.pay(orderDto);
        Assert.assertEquals(orderDto.getOrderStatus(), OrderDetailEnum.IS_COMPLETE.getCode());
    }

    @Test
    public void findAll(){
        PageRequest page =new PageRequest(0,10);
        Page<OrderDto> orderDtoPage = orderService.findAllList(page);
        Assert.assertNotNull(orderDtoPage);
    }
}