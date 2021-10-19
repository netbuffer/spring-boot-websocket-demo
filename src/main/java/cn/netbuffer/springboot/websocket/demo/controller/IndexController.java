package cn.netbuffer.springboot.websocket.demo.controller;

import cn.netbuffer.springboot.websocket.demo.websocket.handler.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sessions", SessionManager.sessions());
        return "index";
    }

}
