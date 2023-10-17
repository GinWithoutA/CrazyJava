package com.ginwithouta.redis.controller;

import com.ginwithouta.redis.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Package : com.ginwithouta.redis.controller
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@RestController
@Slf4j
@RequestMapping(value = "/")
@Tag(name = "orderController", description = "订单控制器")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Operation(description = "新增订单")
    @PostMapping(value = "order/add")
    public void addOrder() {
        orderService.addOrder();
    }
    @Operation(description = "获取订单")
    @GetMapping(value = "order/{keyId}")
    public String getOrderById(@PathVariable Integer keyId) {
        return orderService.getOrderById(keyId);

    }
}
