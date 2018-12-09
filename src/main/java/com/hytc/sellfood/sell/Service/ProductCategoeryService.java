package com.hytc.sellfood.sell.Service;

import com.hytc.sellfood.sell.ObjectMapper.ProductCategoery;
import com.hytc.sellfood.sell.VO.ResultVO;

import java.util.List;

public interface ProductCategoeryService {

    /**
     *  查找所有商品分类
     * @return
     */
    List<ProductCategoery> findAll();


    /**
     * 查找特定的
     * @param productCategoeryId  商品分类Id
     * @return
     */
    ProductCategoery findOne(Integer productCategoeryId);

    /**
     * 根据 Type 查找productCategory
     * @param productCategoeryTypeList
     * @return
     */
    List<ProductCategoery> findProducts(List<Integer> productCategoeryTypeList);

    /**
     * 新增一个商品分类
     * @param productCategoery 商品信息
     * @return
     */
    Boolean save(ProductCategoery productCategoery);

    /**
     * 删除一个商品分类
     * @param productCategoeryId 商品分类Id
     * @return
     */
    Boolean deleteOne(Integer productCategoeryId);

    /**
     * 查找所有的信息,包括商品详细，类目等等
     */
    ResultVO findAllInfos();
}
