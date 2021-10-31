package cn.netbuffer.springboot.websocket.demo.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

@Slf4j
public class PersistenceToDatabaseWebSocketHandlerDecorator extends WebSocketHandlerDecorator {

    public PersistenceToDatabaseWebSocketHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
    }

    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        try {
            this.getDelegate().handleMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (message instanceof TextMessage) {
            log.debug("save text message[{}] to database", message.getPayload());
            //todo 这里可以基于任意的jdbc框架来写库
        } else if (message instanceof BinaryMessage) {
            log.debug("save binary message to database");
        }
    }
}
