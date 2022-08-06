package com.holdmelol.someshop.controllers;

import com.holdmelol.someshop.services.SessionObjHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class MainController {

    private final SessionObjHolder sessionObjHolder;

    public MainController(SessionObjHolder sessionObjHolder) {
        this.sessionObjHolder = sessionObjHolder;
    }

    @RequestMapping({"", "/"})
    public String index(Model model, HttpSession httpSession) {
        model.addAttribute("amountClicks", sessionObjHolder.getAmountClicks());
        if (httpSession.getAttribute("myId") == null) {
            String uuid = UUID.randomUUID().toString();
            httpSession.setAttribute("myId", uuid);
            System.out.println("Generated UUID - > " + uuid);
        }
        model.addAttribute("uuid", httpSession.getAttribute("myId"));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
