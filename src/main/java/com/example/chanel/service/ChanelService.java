package com.example.chanel.service;

import com.example.chanel.pojo.User;
import com.example.chanel.repository.ChanelRepository;
import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@CrossOrigin
@Service
public class ChanelService {
    private String email;
    private String code;
    @Autowired
    private ChanelRepository repository;

    public boolean sendCode(String email, HttpServletRequest req, HttpServletResponse resp) {
        this.email = email;
        System.out.println(this.email);
        // 发件人电子邮箱和授权码
        String from = "2678188220@qq.com";
        // qq授权码
        String authorizationCode = "iaagejosawacdjaf";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        // 开启 SSL 加密
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // 设置用户认证
        properties.put("mail.smtp.auth", "true");

        // 获取默认的 Session 对象
        Session session = Session.getInstance(properties, new Authenticator() {
            public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, authorizationCode);
            }
        });
        // 这里会变成四位数的随机数
        code = String.valueOf((int) (Math.random() * 9000) + 1000);
        System.out.println(code);
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // 设置发件人
            message.setFrom(new InternetAddress(from));

            // 设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // 设置邮件主题
            message.setSubject("您的CHANEL验证码");
            // 设置邮件正文
            message.setText("不要告诉任何人o<br>" + code);

            // 发送邮件
            try {
                Transport.send(message);
            } catch (SMTPSendFailedException sf) {
                return false;
            }
            System.out.println("发送短信成功了老弟");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return true;
    }

    public void signUp(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> params) {
        // ==null说明是没有注册
        if (params == null || repository.findByEmail(email) != null) { // != null 代表有账号了不能重复登录
            return;
        }
        Map<String, Object> param = (Map<String, Object>) params.get("params");
        repository.save(new User(param.get("username").toString(), param.get("password").toString(), email));

        Cookie cookie = new Cookie("userName", param.get("username").toString());
        resp.addCookie(getCookie(param, cookie));

    }

    public Cookie getCookie(Map<String, Object> param, Cookie cookie) {
        cookie.setMaxAge(60 * 60 * 24); //有效期为30天
        cookie.setPath("/");
        return cookie;
    }

    public void signIn(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> params) {

        Map<String, Object> param = (Map<String, Object>) params.get("params");
        User userEmail = repository.findByEmail(param.get("email").toString());
        // 如果是“”代表没有登录过
        if (userEmail == null) { // 如果==null说明根本没有这个数据，也说明没有登录过可以注册
            return;
        }
        if (!userEmail.getEmail().equals(param.get("email")) || !userEmail.getPassword().equals(param.get("password"))) {
            return;
        }
        System.out.println("发送cookie");
        Cookie cookie = new Cookie("userName", param.get("email").toString());
        resp.addCookie(getCookie(param, cookie));
    }

    public Map<String, Object> validateCode(Map<String, Object> codeValue) {
        System.out.println(email);
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code", true);
        stringObjectHashMap.put("email", true);
        if (code == null || code.equals("") || !code.equals(codeValue.get("code"))) {
            // 说明验证码不正确
            stringObjectHashMap.put("code", false);
            return stringObjectHashMap;
        }

        //        != null 表示有这个email了
        System.out.println(repository.findByEmail(email));
        if (repository.findByEmail(email) != null) {
            stringObjectHashMap.put("email", false);
            return stringObjectHashMap;
        }
        System.out.println(code.equals(codeValue.get("code")));
        return stringObjectHashMap;
    }
}
