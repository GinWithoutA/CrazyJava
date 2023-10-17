package com.ginwithouta.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Package : com.ginwithouta.rabbit.config
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc :
 */
@Component
@Slf4j
public class MyReturnCallBack implements RabbitTemplate.ReturnsCallback {

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息从交换机没有正确的路由到队列，原因为：{}", returnedMessage.getMessage());
    }
}
