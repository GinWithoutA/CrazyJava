package com.ginwithouta.rabbitmq.service.impl;

import com.ginwithouta.rabbitmq.service.MessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.util.Date;

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
        String msg = "Hello word";
        Message message = new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.fanout", "", msg);
        log.info("消息发送完毕，发送时间为:{}", new Date());
    }
}
