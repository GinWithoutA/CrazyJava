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
    @Value(value = "${custom.queueName}")
    private String queueName;
    /**
     * 定义正常交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(exchangeName).build();
    }
    /**
     * 定义正常队列
     * @return
     */
    @Bean
    public Queue queue() {
        return QueueBuilder.durable(queueName).deadLetterExchange(exchangeName).build();
    }

    /**
     * 交换机绑定队列
     * @param directExchange
     * @param queue 指定的key的名字，默认是队列的方法名，除非你在@Bean指定名称
     * @return
     */
    @Bean
    public Binding bindingNormal(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("info");
    }


}
