package cn.netbuffer.springboot.websocket.demo.config;

import cn.netbuffer.springboot.websocket.demo.websocket.handler.TextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@Configuration
public class SpringWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(buildTextHandler(), "/ws").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler buildTextHandler() {
        return new TextHandler();
    }

}