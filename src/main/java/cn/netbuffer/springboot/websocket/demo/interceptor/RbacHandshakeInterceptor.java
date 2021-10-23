package cn.netbuffer.springboot.websocket.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class RbacHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.debug("beforeHandshake {} to {} websocket request", serverHttpRequest.getMethodValue(), serverHttpRequest.getURI());
        log.debug("serverHttpRequest.getHeaders()={}", serverHttpRequest.getHeaders());
        serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
//        boolean result = handleByCookie(serverHttpRequest);
//        boolean result = handleByQueryString(serverHttpRequest);
        boolean result = handleByHttpSession(serverHttpRequest);
        return result;
    }

    private boolean handleByCookie(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
            Cookie[] cookies = servletServerHttpRequest.getServletRequest().getCookies();
            Optional<Cookie> cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("token")).findFirst();
            if (cookie.isPresent()) {
                String token = cookie.get().getValue();
                log.debug("find token from cookie={}", token);
                //todo parse value
                return true;
            } else {
                log.warn("not found token from cookie");
                return false;
            }
        }
        return false;
    }

    private boolean handleByQueryString(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
            String token = servletServerHttpRequest.getServletRequest().getParameter("token");
            if (StringUtils.hasLength(token)) {
                log.debug("find token from querystring={}", token);
                //todo parse value
                return true;
            } else {
                log.warn("not found token from querystring");
                return false;
            }
        }
        return false;
    }

    private boolean handleByHttpSession(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            HttpSession httpSession = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getSession();
            if (httpSession.getAttribute("isLogin") != null && (Boolean) (httpSession.getAttribute("isLogin"))) {
                log.debug("check session login success");
                return true;
            } else {
                log.debug("check session login fail");
                return false;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.debug("afterHandshake {}", serverHttpRequest.getURI());
    }

}