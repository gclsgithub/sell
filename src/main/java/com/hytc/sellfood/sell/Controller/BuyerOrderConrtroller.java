package com.hytc.sellfood.sell.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.sellfood.sell.Dto.OrderDto;
import com.hytc.sellfood.sell.Exception.SellException;
import com.hytc.sellfood.sell.Form.OrderForm;
import com.hytc.sellfood.sell.ObjectMapper.OrderDetial;
import com.hytc.sellfood.sell.Service.BuyerService;
import com.hytc.sellfood.sell.Service.OrderService;
import com.hytc.sellfood.sell.VO.ResultVO;
import com.hytc.sellfood.sell.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderConrtroller {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param result
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> createOrder(@Valid OrderForm orderForm, BindingResult result) {
        if (result.hasErrors()) {
            throw new SellException(ResultEnum.PRRAM_NOT_TRUE, result.getFieldError().getDefaultMessage());
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        OrderDto orderDto = coverOrderDto(orderForm);

        OrderDto resultDto = orderService.create(orderDto);

        resultVO.setCode("0");
        resultVO.setMsg(null);
        Map<String, String> data = new HashMap<>();
        data.put("orderId", resultDto.getOrderId());
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 订单列表
     *
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDto>> listOrder(@RequestParam("openid") String openId,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {

        ResultVO<List<OrderDto>> resultVO = new ResultVO<>();

        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单】用户openid 为空");
            throw new SellException(ResultEnum.PRRAM_NOT_TRUE);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openId, pageRequest);

        resultVO.setData(orderDtoPage.getContent());
        resultVO.setMsg("成功");
        resultVO.setCode("0");
        return resultVO;
    }

    /**
     * 取消订单
     *
     * @param openId
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDto> orderDetial(@RequestParam("openid") String openId,
                                          @RequestParam("orderId") String orderId) {

        OrderDto orderDto = buyerService.findOneOrder(openId,orderId);

        ResultVO<OrderDto> resultVO = new ResultVO<>();
        resultVO.setMsg("成功");
        resultVO.setCode("0");

        resultVO.setData(orderDto);
        return resultVO;
    }

    /**
     * 取消订单
     *
     * @param openId
     * @param orderId
     * @return
     */
    @PostMapping("cancel")
    public ResultVO cancle(@RequestParam("openid") String openId,
                           @RequestParam("orderId") String orderId) {

        //TODO
        //安全认证
        ResultVO resultVO = new ResultVO();

        buyerService.cancleOrder(openId,orderId);
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        return resultVO;
    }


    private OrderDto coverOrderDto(OrderForm orderForm) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerOpenId(orderForm.getOpenid());
        orderDto.setBuyerTel(orderForm.getPhone());
        List<OrderDetial> orderDetialList = null;
        try {
            orderDetialList = objectMapper.readValue(orderForm.getItems(), new TypeReference<List<OrderDetial>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            log.error("jason转换异常");
            throw new SellException(ResultEnum.PRRAM_NOT_TRUE, "未输入正确的购买信息");
        }

        if (CollectionUtils.isEmpty(orderDetialList)) {
            log.error(ResultEnum.CAR_EMPTY.getMessage());
            throw new SellException(ResultEnum.CAR_EMPTY, "购物车不能为空");
        }
        orderDto.setOrderDetialList(orderDetialList);

        return orderDto;
    }

}
