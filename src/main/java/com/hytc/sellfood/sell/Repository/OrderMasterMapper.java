package com.hytc.sellfood.sell.Repository;

import com.hytc.sellfood.sell.ObjectMapper.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterMapper extends JpaRepository<OrderMaster,String> {

    /**
     * 按照买家的openId查询订单
     *
     * @param openId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenId(String openId, Pageable pageable);
}
