package com.ginwithouta.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
    @RabbitListener(queues = {"queue.fanout.first", "queue.fanout.second"})
    public void receiveMsg(Message message) {
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("接收到的消息为：{}", msg);
    }
}
