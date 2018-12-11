package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;


    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("1");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfos = productService.findAll(pageRequest);
        Assert.assertEquals(productInfos.getContent().size(),1);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo>  productInfos = productService.findUpAll();

        Assert.assertEquals(productInfos.size(),1);
    }

    @Test
    public void savaProductInfo() {

        ProductInfo p = new ProductInfo();
        p.setCategoeryType("99");
        p.setProductStock(100L);
        p.setProductIcon(null);
        p.setProductSinglePrice(new BigDecimal("3.12"));
        p.setProductName("皮蛋瘦肉粥");
        p.setDelFlag("0");
        p.setProductStatus(0);
        p.setProductDescription("好吃不贵");
        p.setProductId("2");
        productService.savaProductInfo(p);
    }

    @Test
    public void deleteByProductId() {
        productService.deleteByProductId("2");
    }

    @Test
    public void updateProductCount() {
    }
}