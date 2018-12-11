package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.Dto.CartDto;
import com.hytc.sellfood.sell.Exception.ProductException;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.ProductService;
import com.hytc.sellfood.sell.enums.ProductStatus;
import com.hytc.sellfood.sell.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    /**
     * 加库存
     *
     * @param cartDtoList
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {

        for (CartDto car :cartDtoList) {
            ProductInfo info = productMapper.findByProductId(car.getProductId());
            if (ObjectUtils.isEmpty(info)){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Long count = info.getProductStock()+Long.valueOf(car.getProductQuantity());

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
}
