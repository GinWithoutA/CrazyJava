package com.ginwithouta.rabbit;

import com.ginwithouta.rabbit.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package : com.ginwithouta.rabbit
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc :
 */
@SpringBootApplication
public class Return1Application implements ApplicationRunner {
    @Resource
    private MessageService messageService;
    public static void main(String[] args) {
        SpringApplication.run(Return1Application.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMsg();
    }
}
