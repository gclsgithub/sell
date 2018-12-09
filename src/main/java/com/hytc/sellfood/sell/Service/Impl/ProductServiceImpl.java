package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.ProductService;
import com.hytc.sellfood.sell.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;

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
        productMapper.save(productInfo);
    }


    @Override
    public void deleteByProductId(String productId) {
        productMapper.deleteById(productId);
    }

    @Override
    public void updateProductCount(String productId, Integer changeCount) {
        ProductInfo productInfo = productMapper.findById(productId).get();
        productInfo.setProductStock(productInfo.getProductStock() - new Long(changeCount));
        productMapper.save(productInfo);
    }
}
