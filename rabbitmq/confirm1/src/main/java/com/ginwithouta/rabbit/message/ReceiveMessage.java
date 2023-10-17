package com.ginwithouta.rabbit.message;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Package : com.ginwithouta.rabbit.message
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc :
 */
@Component
@Slf4j
public class ReceiveMessage {
    @RabbitListener(queues = "queue.delay.dlx.2")
    public void receiveMsg(Message message) {
        String body = new String(message.getBody());
        log.info("接收到的消息：{}，接收时间为：{}", body, new Date());
    }

}
