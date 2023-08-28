package com.example.chanel.controller;

import com.example.chanel.service.ChanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChanelController {
    @Autowired
    ChanelService chanelService;

    @RequestMapping("/chanel")
    @ResponseBody
    public String index(@RequestBody HashMap<String, Object> data) {

        int o = (int) data.get("id");
        System.out.println(o);
        return "o";
    }

    @PostMapping("/code.do")
    @ResponseBody
    public boolean sendCode(@RequestBody HashMap<String, Object> data, HttpServletResponse resp,
                            HttpServletRequest req) {
        Map<String, Object> email = (Map<String, Object>) data.get("params");
        // not null true
        return chanelService.sendCode(email.get("email").toString(), req, resp);
    }

    @PostMapping("/signUp.do")
    public void login(HttpServletRequest req, HttpServletResponse resp, @RequestBody Map<String, Object> params) {
        chanelService.signUp(req, resp, params);
    }

    @PostMapping("/validateCode.do")
    @ResponseBody
    public Map<String, Object> validateCode(@RequestBody Map<String, Object> codeValue) {
        return chanelService.validateCode(codeValue);
    }

    @PostMapping("/signIn.do")
    @ResponseBody
    public void validateCode(HttpServletRequest req, HttpServletResponse resp, @RequestBody Map<String, Object> params) {
        chanelService.signIn(req, resp, params);
    }

}
