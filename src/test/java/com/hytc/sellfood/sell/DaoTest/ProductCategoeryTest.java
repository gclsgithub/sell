package com.hytc.sellfood.sell.DaoTest;

import com.hytc.sellfood.sell.ObjectMapper.ProductCategoery;
import com.hytc.sellfood.sell.Repository.ProductCategoeryMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductCategoeryTest {

    @Autowired
    private ProductCategoeryMapper productCategoeryMapper;

    @Test
    public void Test1() {
        ProductCategoery productCategoery2 = productCategoeryMapper.findOne(1);
        System.out.print(productCategoery2);
    }

    @Test
    public void findAll(){
        List<ProductCategoery> productCategoeryList = productCategoeryMapper.findAll();
        productCategoeryList.stream().forEach(prodct ->System.out.println(prodct.getCategoeryName()));
    }

    @Test
    public void finaById(){
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(4);
        List<ProductCategoery> categoeries = productCategoeryMapper.findByCategoeryIdIn(lists);
        categoeries.stream().forEach(prodct ->System.out.println(prodct.getCategoeryName()));
    }

    @Test
    public void insertInto(){
        ProductCategoery productCategoery = new ProductCategoery();
        productCategoery.setCategoeryType(3);
        productCategoery.setCategoeryName("男神最爱");
        productCategoeryMapper.save(productCategoery);
    }

    @Test
    public void update(){
        ProductCategoery productCategoery = productCategoeryMapper.findOne(1);
        productCategoery.setCategoeryType(99);
        productCategoeryMapper.save(productCategoery);
    }

    @Test
    public void del(){
        productCategoeryMapper.delete(1);
    }

    @Test
    public void findByCategoeryTypeIn(){
        List<Integer> typeList = new ArrayList<>();
        typeList.add(99);

        List<ProductCategoery> categoeries = productCategoeryMapper.findByCategoeryTypeIn(typeList);

        Assert.assertNotNull(categoeries);
    }

}
