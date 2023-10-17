package com.ginwithouta.direct.service.impl;

import com.ginwithouta.direct.service.MessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class MessageServiceImpl implements MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg() {
        Message msg = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.direct", "happy", msg);
        log.info("消息发送完毕，发送时间为：{}", new Date());
    }

    // @Override
    // public void sendMsg() {
    //     MessageProperties messageProperties = new MessageProperties();
    //     messageProperties.setExpiration("15000");
    //     Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
    //     rabbitTemplate.convertAndSend("exchange.direct", "info", message);
    //     log.info("消息发送完毕，发送时间为：{}", new Date());
    // }
}
