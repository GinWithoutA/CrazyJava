package com.ginwithouta.rabbit.service.impl;

import com.ginwithouta.rabbit.service.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
public class MessageServiceImpl implements MessageService {

    @Value(value = "${custom.exchangeName}")
    private String exchangeName;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("消息没有到达交换机，原因为：{}", cause);
                // TODO: 重发消息或者记录日志
            }
        }));
    }

    @Override
    public void sendMsg() {
        Message msg = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend(exchangeName, "danm", msg);
        log.info("消息发送完毕，发8878888878送时间为：{}", new Date());
    }
}
