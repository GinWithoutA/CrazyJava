package com.ginwithouta.redis.controller;

import com.ginwithouta.redis.service.InventoryServcie;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package : com.ginwithouta.redis.controller
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@RestController
@Tag(name = "InventoryController", description = "redis分布式锁")
@RequestMapping(value = "/")
public class InventoryController {
    @Autowired
    private InventoryServcie inventoryServcie;
    @Operation(description = "扣减库存，一次卖一个")
    @GetMapping(value = "inventory/sale")
    public String sale() {
        return inventoryServcie.sale();
    }
    @Operation(description = "扣减库存，一次卖一个")
    @GetMapping(value = "inventory/saleByRedisson")
    public String saleByRedisson() {
        return inventoryServcie.saleByRedisson();
    }
}
