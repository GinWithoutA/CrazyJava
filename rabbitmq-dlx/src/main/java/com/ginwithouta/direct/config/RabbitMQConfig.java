package com.ginwithouta.direct.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Package : com.ginwithouta.direct.config
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Configuration
public class RabbitMQConfig {
    /**
     * 定义正常交换机
     * @return
     */
    @Bean
    public DirectExchange normalExchange() {
        return ExchangeBuilder.directExchange("exchange.normal").build();
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {
        return ExchangeBuilder.directExchange("exchange.dead").build();
    }

    /**
     * 定义正常队列
     * @return
     */
    @Bean
    public Queue normalQueue() {
        Map<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-message-ttl", 20000);
        arguments.put("x-dead-letter-exchange", "exchange.dead");
        arguments.put("x-dead-letter-routing-key", "error");
        return QueueBuilder.durable("queue.dlx.normal").withArguments(arguments).build();
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable("queue.dlx.dead").build();
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
     * @param deadExchange
     * @param deadQueue
     * @return
     */
    @Bean
    public Binding bindingDead(DirectExchange deadExchange, Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("error");
    }

}
