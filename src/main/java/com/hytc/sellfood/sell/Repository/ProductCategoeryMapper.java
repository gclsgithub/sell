package com.hytc.sellfood.sell.Repository;

import com.hytc.sellfood.sell.ObjectMapper.ProductCategoery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoeryMapper extends JpaRepository<ProductCategoery, Integer> {

    /**
     * 根据类别Id查找类别
     *
     * @param categoeryIdList Id List
     * @return
     */
    List<ProductCategoery> findByCategoeryIdIn(List<Integer> categoeryIdList);

    /**
     * 根据type查找
     *
     * @param categoeryList
     * @return
     */
    List<ProductCategoery> findByCategoeryTypeIn(List<Integer> categoeryList);
}
