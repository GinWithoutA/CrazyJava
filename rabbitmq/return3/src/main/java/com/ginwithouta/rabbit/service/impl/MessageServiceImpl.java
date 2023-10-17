package com.ginwithouta.rabbit.service.impl;

import com.ginwithouta.rabbit.service.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Package : com.ginwithouta.direct.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService{

    @Value("${custom.exchangeName}")
    private String exchangeName;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(returnedMessage -> log.error("消息从交换机没有正确的路由到队列，原因为：{}", returnedMessage.getMessage()));
    }

    @Override
    public void sendMsg() {
        Message msg = MessageBuilder.withBody("我的订单信息1".getBytes()).build();
        rabbitTemplate.convertAndSend(exchangeName, msg);
        log.info("消息发送完毕，发送时间为：{}", new Date());
    }
}
