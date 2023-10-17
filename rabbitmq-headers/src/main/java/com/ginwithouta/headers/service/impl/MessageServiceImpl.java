package com.ginwithouta.headers.service.impl;

import com.ginwithouta.headers.service.MessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.ginwithouta.rabbitmq.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Component
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg() {
        MessageProperties messageProperties = new MessageProperties();
        Map<String, Object> map = new HashMap<>();
        map.put("type", "m");
        map.put("status", 1);
        messageProperties.setHeaders(map);
        Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.headers", "", message);
        log.info("消息发送完毕，发送时间为:{}", new Date());
    }
}
