package com.ginwithouta.rabbit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Package : com.ginwithouta.direct.service
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
public interface MessageService {
    /**
     * 发送消息
     */
    void sendMsg();
}
