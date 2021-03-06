package com.hytc.sellfood.sell.Service;

import com.hytc.sellfood.sell.Dto.CartDto;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * 根据Type查找ProductInfo
     *
     * @param categoeryType
     * @return
     */
    List<ProductInfo> findProductByProductCategoeryType(String categoeryType);

    /**
     * 查找商品信息
     *
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有的商品信息
     *
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询上架商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 保存商品信息
     *
     * @param productInfo
     */
    void savaProductInfo(ProductInfo productInfo);


    /**
     * 删除某一个商品
     *
     * @param productId
     */
    void deleteByProductId(String productId);

    /**
     * 更改某一个商品的数量 （包含增加和删除）
     *
     * @param productId
     */
    void updateProductCount(String productId, Integer changeCount);


    /**
     * 加库存
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     *
     * @param cartDtoList
     */
    void decreaseStock(List<CartDto> cartDtoList);

    /**
     * 上架
     *
     * @param productId
     */
    void onSale(String productId);

    /**
     *
     * @param productId
     */
    void offSale(String productId);
}
