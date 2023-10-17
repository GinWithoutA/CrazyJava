package com.ginwithouta.direct;

import com.ginwithouta.direct.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package : com.ginwithouta.direct
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@SpringBootApplication
public class DirectApplication implements ApplicationRunner {

    @Resource
    private MessageService messageService;
    public static void main(String[] args) {
        SpringApplication.run(DirectApplication.class, args);

    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMsg();
    }
}
