package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.ObjectMapper.ProductCategoery;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductCategoeryMapper;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.ProductCategoeryService;
import com.hytc.sellfood.sell.VO.ProductVO;
import com.hytc.sellfood.sell.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoeryServiceImpl implements ProductCategoeryService {

    /**
     * 依赖注入productCategoeryMapper
     */
    @Autowired
    private ProductCategoeryMapper productCategoeryMapper;

    /**
     * 依赖注入prodcutMapper
     *
     * @return
     */
    private ProductMapper productMapper;


    @Override
    public List<ProductCategoery> findAll() {
        return productCategoeryMapper.findAll();
    }

    @Override
    public ProductCategoery findOne(Integer productCategoeryId) {
        return productCategoeryMapper.findById(productCategoeryId).get();
    }

    @Override
    public List<ProductCategoery> findProducts(List<Integer> productCategoeryTypeList) {
        return productCategoeryMapper.findByCategoeryTypeIn(productCategoeryTypeList);
    }

    @Override
    public Boolean save(ProductCategoery productCategoery) {
        ProductCategoery save = productCategoeryMapper.save(productCategoery);
        return (null == save);
    }

    @Override
    public Boolean deleteOne(Integer productCategoeryId) {
        try {
            productCategoeryMapper.deleteById(productCategoeryId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ResultVO findAllInfos() {
        List<ProductCategoery> productCategoeryList = productCategoeryMapper.findAll();
        ResultVO resultVO = new ResultVO();
        if (!CollectionUtils.isEmpty(productCategoeryList)) {
            resultVO.setData(
                    productCategoeryList.parallelStream().map(info ->
                    {
                        ProductVO productVO = new ProductVO();
                        productVO.setProductCategoeryType(info.getCategoeryType());
                        productVO.setProductCategoeryName(info.getCategoeryName());
                        productVO.setProductInfo(productMapper.findByCategoeryType(String.valueOf(info.getCategoeryType())));
                        return productVO;
                    }).collect(Collectors.toList())
            );
            resultVO.setMsg("成功");
            resultVO.setCode("0");
        }
        resultVO.setMsg("类目不存在");
        resultVO.setCode("201");
        return resultVO;
    }
}
