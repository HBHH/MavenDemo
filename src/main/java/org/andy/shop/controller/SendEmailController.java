package org.andy.shop.controller;

import org.andy.shop.model.Email;
import org.andy.shop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by CIFPAY on 2016/1/22.
 * 发送邮件控制类
 *
 *
 */
@Controller
@RequestMapping("/sendEmailController")
public class SendEmailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/sendEmail")
    public String sendEmail(ServletRequest request) {
        return "/email/EmailForm";
    }

    @RequestMapping(value="/sendEmailMessage",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
    public String sendEmailMessage(HttpServletRequest request){
        String recipientAddress = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        Email email =new Email();
        email.setToAddress(recipientAddress);
        email.setSubject(subject);
        email.setContent(message);
        try {
            mailService.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/email/Result";
    }

}
