package cn.netbuffer.springboot.websocket.demo.websocket.handler;

import org.springframework.web.socket.WebSocketSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    private SessionManager() {
    }

    public static WebSocketSession put(String key,WebSocketSession webSocketSession) {
        return sessions.put(key,webSocketSession);
    }

    public static WebSocketSession get(String key) {
        return sessions.get(key);
    }

    public static WebSocketSession remove(String key) {
        return sessions.remove(key);
    }
}
