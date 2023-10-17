package com.ginwithouta.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.ginwithouta.direct.config
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Configuration
public class RabbitMQConfig {
    @Value(value = "${custom.exchangeName}")
    private String exchangeName;
    @Value(value = "${custom.queueNormalName}")
    private String queueNormalName;
    @Value(value = "${custom.queueDlxName}")
    private String queueDlxName;
    /**
     * 定义正常交换机
     * @return
     */
    @Bean
    public DirectExchange normalExchange() {
        return ExchangeBuilder.directExchange(exchangeName).build();
    }
    /**
     * 定义正常队列
     * @return
     */
    @Bean
    public Queue normalQueue() {
        return QueueBuilder.durable(queueNormalName).ttl(25000).deadLetterExchange(exchangeName).deadLetterRoutingKey("error").build();
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(queueDlxName).build();
    }

    /**
     * 正常交换机绑定正常队列
     * @param normalExchange
     * @param normalQueue 指定的key的名字，默认是队列的方法名，除非你在@Bean指定名称
     * @return
     */
    @Bean
    public Binding bindingNormal(DirectExchange normalExchange, Queue normalQueue) {
        return BindingBuilder.bind(normalQueue).to(normalExchange).with("order");
    }

    /**
     * 死信交换机绑定死信队列
     * @param normalExchange
     * @param deadQueue
     * @return
     */
    @Bean
    public Binding bindingDead(DirectExchange normalExchange, Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(normalExchange).with("error");
    }

}
