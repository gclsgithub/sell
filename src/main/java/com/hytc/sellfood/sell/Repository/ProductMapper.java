package com.hytc.sellfood.sell.Repository;

import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMapper extends JpaRepository<ProductInfo,String> {

    /**
     * 按照类别查找
     * @param categoeryTypeList
     * @return
     */
    List<ProductInfo> findByCategoeryTypeIn(List<Integer> categoeryTypeList);

    /**
     * 根据productId（String）类型查找
     * @param productId
     * @return
     */
    ProductInfo findByProductId(String productId);

    /**
     * 根据状态查找
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(int status);

    /**
     * 根据Type 查找单个ProductInfo
     * @param categoery
     */
    List<ProductInfo> findByCategoeryType(String categoery);
}
