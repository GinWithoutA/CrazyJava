package com.ginwithouta.headers;

import com.ginwithouta.headers.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package : com.ginwithouta.rabbitmq
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@SpringBootApplication
public class HeadersApplication implements ApplicationRunner {

    @Resource
    private MessageService messageService;
    public static void main(String[] args) {
        SpringApplication.run(HeadersApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMsg();
    }
}
