package cn.netbuffer.springboot.websocket.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Slf4j
@EnableAsync
@EnableWebSocket
@SpringBootApplication
public class SpringBootWebsocketDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebsocketDemoApplication.class, args);
    }

}
