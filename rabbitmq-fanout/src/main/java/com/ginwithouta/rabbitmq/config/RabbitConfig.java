package com.ginwithouta.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public DirectExchange fanoutExchange() {
        return ExchangeBuilder.directExchange("exchange.fanout").build();
    }

    /**
     * 定义两个队列
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return new Queue("queue.fanout.first");
    }

    @Bean
    public Queue secondQueue() {
        return QueueBuilder.("queue.fanout.second");
    }

    /**
     * 交换机和队列绑定，绑定需要指定key
     * @param fanoutExchange
     * @param firstQueue 指定的key的名字，默认是队列的方法名，除非你在@Bean指定名称
     * @return
     */
    @Bean
    public Binding bindingFirst(FanoutExchange fanoutExchange, Queue firstQueue) {
        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingSecond(FanoutExchange fanoutExchange, Queue secondQueue) {
        return BindingBuilder.bind(secondQueue).to(fanoutExchange);
    }
}
