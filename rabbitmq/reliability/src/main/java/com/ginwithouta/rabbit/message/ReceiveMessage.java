package com.ginwithouta.rabbit.message;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Package : com.ginwithouta.rabbit.message
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周三
 * @Desc :
 */
@Component
@Slf4j
public class ReceiveMessage {
    @RabbitListener(queues = {"queue.reliability.1"})
    public void receiveMsg(Message message, Channel channel) {
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("接收到的消息为：{}", new String(message.getBody()));
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("消息处理出现问题");
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
