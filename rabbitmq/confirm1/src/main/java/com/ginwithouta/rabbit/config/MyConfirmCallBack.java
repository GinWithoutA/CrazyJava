package com.ginwithouta.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Package : com.ginwithouta.rabbit.config
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc :
 */
@Configuration
@Slf4j
public class MyConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息正确的到达交换机");
            return ;
        }
        log.error("消息没有到达交换机，原因为：{}", cause);
    }
}
