package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.Dto.CartDto;
import com.hytc.sellfood.sell.Exception.ProductException;
import com.hytc.sellfood.sell.Exception.SellException;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.ProductService;
import com.hytc.sellfood.sell.enums.ProductStatus;
import com.hytc.sellfood.sell.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductInfo> findProductByProductCategoeryType(String categoeryType) {
        return productMapper.findByCategoeryType(Integer.valueOf(categoeryType));
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productMapper.findByProductId(productId);

    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productMapper.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productMapper.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public void savaProductInfo(ProductInfo productInfo) {

        //获取Id的最大值
        List<ProductInfo> productInfos = productMapper.findAll();
        List<String> idList =  productInfos.parallelStream().map(ProductInfo::getProductId).collect(Collectors.toList());
        String max = Collections.max(idList);
        productInfo.setProductId(String.valueOf(Integer.valueOf(max)+1));

        productMapper.save(productInfo);
    }


    @Override
    public void deleteByProductId(String productId) {
        productMapper.delete(productId);
    }

    @Override
    public void updateProductCount(String productId, Integer changeCount) {
        ProductInfo productInfo = productMapper.findOne(productId);
        productInfo.setProductStock(productInfo.getProductStock() - new Long(changeCount));
        productMapper.save(productInfo);
    }

    /**
     * 加库存
     *
     * @param cartDtoList
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {

        for (CartDto car : cartDtoList) {
            ProductInfo info = productMapper.findByProductId(car.getProductId());
            if (ObjectUtils.isEmpty(info)) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Long count = info.getProductStock() + Long.valueOf(car.getProductQuantity());

            info.setProductStock(count);
            productMapper.save(info);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto car : cartDtoList) {
            ProductInfo productInfo = productMapper.findByProductId(car.getProductId());
            if (ObjectUtils.isEmpty(productInfo)) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Long count = productInfo.getProductStock() - Long.valueOf(car.getProductQuantity());

            if (count < 0) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            //这种情况是计算得到的内存可以更新
            productInfo.setProductStock(count);
            productMapper.save(productInfo);
        }
    }

    @Override
    public void onSale(String productId) {

        ProductInfo productInfo = productMapper.findByProductId(productId);

        if (ObjectUtils.isEmpty(productInfo)) {
            log.error("【商品上架功能】商品不存在");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }

        //验证状态是否是可以上架的状态 状态 0 可以上架
        if (ProductStatus.UP.getCode().equals(productInfo.getProductStatus())) {
            productInfo.setProductStatus(ProductStatus.DOWN.getCode());
        } else {
            log.error("【商品上架功能】商品状态不正确");
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productMapper.save(productInfo);
    }

    @Override
    public void offSale(String productId) {

        ProductInfo productInfo = productMapper.findByProductId(productId);

        if (ObjectUtils.isEmpty(productInfo)) {
            log.error("【商品上架功能】商品不存在");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }

        //验证状态是否是可以上架的状态 状态 0 可以上架
        if (ProductStatus.DOWN.getCode().equals(productInfo.getProductStatus())) {
            productInfo.setProductStatus(ProductStatus.UP.getCode());
        } else {
            log.error("【商品上架功能】商品状态不正确");
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productMapper.save(productInfo);
    }
}
