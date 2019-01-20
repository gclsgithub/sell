package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.ObjectMapper.ProductCategoery;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductCategoeryMapper;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.ProductCategoeryService;
import com.hytc.sellfood.sell.Service.ProductService;
import com.hytc.sellfood.sell.VO.ApiProductInfoVo;
import com.hytc.sellfood.sell.VO.ProductVO;
import com.hytc.sellfood.sell.VO.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoeryServiceImpl implements ProductCategoeryService {

    /**
     * 依赖注入productCategoeryMapper
     */
    @Autowired
    private ProductCategoeryMapper productCategoeryMapper;

    @Autowired
    private ProductService productService;

    /**
     * 依赖注入prodcutMapper
     *
     * @return
     */
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductCategoery> findAll() {
        return productCategoeryMapper.findAll();
    }

    @Override
    public ProductCategoery findOne(Integer productCategoeryId) {
        return productCategoeryMapper.findOne(productCategoeryId);
    }

    @Override
    public List<ProductCategoery> findProducts(List<Integer> productCategoeryTypeList) {
        return productCategoeryMapper.findByCategoeryTypeIn(productCategoeryTypeList);
    }

    @Override
    @Transactional
    public Boolean save(ProductCategoery productCategoery) {

        ProductCategoery oldCategoery = productCategoeryMapper.findOne(productCategoery.getCategoeryId());

        List<ProductInfo> productInfos
                = productService.findProductByProductCategoeryType(String.valueOf(oldCategoery.getCategoeryType()));

        //如果为空，没有要更改的商品
        if (ObjectUtils.isEmpty(productInfos)) {
            return false;
        }

        productInfos.stream().forEach(productInfo -> {
            productInfo.setCategoeryType(productCategoery.getCategoeryType());
            productService.savaProductInfo(productInfo);
        });

        ProductCategoery categoery = productCategoeryMapper.save(productCategoery);
        return true;
    }

    @Override
    public Boolean deleteOne(Integer productCategoeryId) {
        try {
            productCategoeryMapper.delete(productCategoeryId);
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
                        productVO.setType(info.getCategoeryType());
                        productVO.setProductCategoeryName(info.getCategoeryName());
                        List<ProductInfo>  daoMapDto =  productMapper.findByCategoeryType(info.getCategoeryType());
                        productVO.setProductInfo(daoMapDto.stream().map(
                                info1 ->{
                                    ApiProductInfoVo vo= new ApiProductInfoVo();
                                    BeanUtils.copyProperties(info1, vo);
                                    return vo;
                                }).collect(Collectors.toList()));
                        return productVO;
                    }).collect(Collectors.toList())
            );
            resultVO.setMsg("成功");
            resultVO.setCode("0");
        } else {
            resultVO.setMsg("类目不存在");
            resultVO.setCode("201");
        }
        return resultVO;
    }
}
