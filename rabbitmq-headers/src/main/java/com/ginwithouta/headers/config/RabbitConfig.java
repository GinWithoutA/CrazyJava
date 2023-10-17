package com.ginwithouta.headers.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Package : com.ginwithouta.rabbitmq.config
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : RabbitMQ 三部曲 交换机 队列 绑定交换机和队列
 */
@Configuration
public class RabbitConfig {

    /**
     * 定义交换机
     * @return
     */
    @Bean
    public HeadersExchange headersExchange() {
        return ExchangeBuilder.headersExchange("exchange.headers").build();
    }

    /**
     * 定义两个队列
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return new Queue("queue.headers.first");
    }

    @Bean
    public Queue secondQueue() {
        return QueueBuilder.durable("queue.headers.second").build();
    }

    /**
     * 交换机和队列绑定，绑定需要指定key
     * @param headersExchange
     * @param firstQueue 指定的key的名字，默认是队列的方法名，除非你在@Bean指定名称
     * @return
     */
    @Bean
    public Binding bindingFirst(HeadersExchange headersExchange, Queue firstQueue) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "m");
        map.put("status", 1);
        return BindingBuilder.bind(firstQueue).to(headersExchange).whereAll(map).match();
    }

    @Bean
    public Binding bindingSecond(HeadersExchange headersExchange, Queue secondQueue) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "s");
        map.put("status", 0);
        return BindingBuilder.bind(secondQueue).to(headersExchange).whereAll(map).match();
    }
}
