package com.hytc.sellfood.sell.Controller;

import com.hytc.sellfood.sell.Service.ProductCategoeryService;
import com.hytc.sellfood.sell.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductCategoeryService productCategoeryService;

    @RequestMapping(path = "/list")
    public ResultVO list(){

        ResultVO vo = productCategoeryService.findAllInfos();

        return vo;

    }
}
