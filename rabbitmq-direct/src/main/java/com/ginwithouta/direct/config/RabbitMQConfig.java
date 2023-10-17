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
     * 定义交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange("exchange.direct").build();
    }

    /**
     * 定义两个队列
     * @return
     */
    @Bean
    public Queue firstQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 15000);
        return QueueBuilder.durable("queue.direct.ttl").withArguments(arguments).build();
    }

    // @Bean
    // public Queue secondQueue() {
    //     return QueueBuilder.durable("queue.direct.second").build();
    // }

    /**
     * 交换机和队列绑定，绑定需要指定key
     * @param directExchange
     * @param firstQueue 指定的key的名字，默认是队列的方法名，除非你在@Bean指定名称
     * @return
     */
    @Bean
    public Binding bindingFirst(DirectExchange directExchange, Queue firstQueue) {
        return BindingBuilder.bind(firstQueue).to(directExchange).with("info");
    }

    // @Bean
    // public Binding bindingSecondFirst(DirectExchange directExchange, Queue secondQueue) {
    //     return BindingBuilder.bind(secondQueue).to(directExchange).with("error");
    // }
    //
    // @Bean
    // public Binding bindingSecondSecond(DirectExchange directExchange, Queue secondQueue) {
    //     return BindingBuilder.bind(secondQueue).to(directExchange).with("info");
    // }
    //
    // @Bean
    // public Binding bindingSecondThird(DirectExchange directExchange, Queue secondQueue) {
    //     return BindingBuilder.bind(secondQueue).to(directExchange).with("warning");
    // }
}
