package com.ginwithouta.rabbit.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Package : com.ginwithouta.message
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Slf4j
@Component
public class ReceiveMessage {
    /**
     * 接收两个队列的消息，程序一启动自动运行
     * @param message
     */
    @RabbitListener(queues = {"queue.normal"})
    public void receiveMsg(Message message, Channel channel) {
        MessageProperties messageProperties = message.getMessageProperties();
        // 获取消息的唯一标识，类似身份证
        long deliveryTag = messageProperties.getDeliveryTag();
        try {
            byte[] body = message.getBody();
            String msg = new String(body);
            log.info("接收到的消息为：{}", msg);
            // 消费者的手动确认，false表示只确认一条消息，true表示确认一批的消息
            int a = 1 / 0;
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("接收者出现问题：{}", e.getMessage());
            try {
                // 消费者的手动确认，前面两个参数和之前的一样，最后一个参数表示是否重新入队
                // channel.basicNack(deliveryTag, false, true);
                // channel.basicNack(deliveryTag, false, false);
                // 参数2表示是否重新入队，不可以批量处理
                channel.basicReject(deliveryTag, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
