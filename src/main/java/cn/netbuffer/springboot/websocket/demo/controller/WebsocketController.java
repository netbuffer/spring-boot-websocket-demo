package cn.netbuffer.springboot.websocket.demo.controller;

import cn.netbuffer.springboot.websocket.demo.websocket.handler.SessionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import java.io.IOException;

@RestController
@RequestMapping("/ws")
public class WebsocketController {

    @GetMapping("{session}/send")
    public void send(@PathVariable("session") String session, String msg) {
        WebSocketMessage webSocketMessage = new TextMessage(msg);
        try {
            SessionManager.get(session).sendMessage(webSocketMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}