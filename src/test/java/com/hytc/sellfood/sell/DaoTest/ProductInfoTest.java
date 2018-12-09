package com.hytc.sellfood.sell.DaoTest;

import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductInfoTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1() {
        ProductInfo info = new ProductInfo();
        info.setProductId("4");
        info.setProductName("女生专属");
        info.setProductSinglePrice(new Double(11.2));
        info.setProductIcon("aaa");
        info.setProductStock(new Long(111));
        info.setCategoeryType("1");
        productMapper.save(info);

        List<ProductInfo> productInfoList = productMapper.findAll();

        System.out.print(productInfoList);
    }

    @Test
    public void test2() {
        List<ProductInfo> productInfoList = productMapper.findByProductStatus(1);
        productInfoList.stream().forEach(prodct -> System.out.println(prodct.getProductName()));
    }

    @Test
    public void test3(){
        List<ProductInfo> productInfos = productMapper.findByCategoeryType("2");
        System.out.println(productInfos);
    }
}
