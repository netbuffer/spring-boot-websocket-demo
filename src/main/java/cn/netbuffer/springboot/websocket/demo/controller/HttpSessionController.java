package cn.netbuffer.springboot.websocket.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/http/session")
public class HttpSessionController {

    @PostMapping("set")
    public void setSessionValue(HttpSession httpSession, String key, String value) {
        httpSession.setAttribute(key, value);
        log.debug("setSessionValue key={} value={}", key, value);
    }

}
