package com.ginwithouta.topic.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public TopicExchange topicExchange() {
        return ExchangeBuilder.topicExchange("exchange.topic").build();
    }

    /**
     * 定义两个队列
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return QueueBuilder.durable("queue.topic.first").build();
    }

    @Bean
    public Queue secondQueue() {
        return QueueBuilder.durable("queue.topic.second").build();
    }

    /**
     * 交换机和队列绑定，绑定需要指定key
     * @param topicExchange
     * @param firstQueue 指定的key的名字，默认是队列的方法名，除非你在@Bean指定名称
     * @return
     */
    @Bean
    public Binding bindingFirst(TopicExchange topicExchange, Queue firstQueue) {
        return BindingBuilder.bind(firstQueue).to(topicExchange).with("*.orange.*");
    }

    @Bean
    public Binding bindingSecondFirst(TopicExchange topicExchange, Queue secondQueue) {
        return BindingBuilder.bind(secondQueue).to(topicExchange).with("*.*.rabbit");
    }

    @Bean
    public Binding bindingSecondSecond(TopicExchange topicExchange, Queue secondQueue) {
        return BindingBuilder.bind(secondQueue).to(topicExchange).with("lazy.#");
    }
}
