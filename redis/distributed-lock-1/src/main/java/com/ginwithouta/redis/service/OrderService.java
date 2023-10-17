package com.ginwithouta.redis.service;

/**
 * @Package : com.ginwithouta.redis.service
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
public interface OrderService {
    void addOrder();
    String getOrderById(Integer keyId);
}
