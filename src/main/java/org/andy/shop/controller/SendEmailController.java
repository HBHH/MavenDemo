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
            System.out.println("发送邮件");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // creates a simple e-mail object
        //SimpleMailMessage email = new SimpleMailMessage();
        //email.setFrom(sendFrom); //126 邮箱非要填写发件人变态的地方
       // email.setTo(recipientAddress);
        //email.setSubject(subject);
       // email.setText(message);

        // sends the e-mail
       // mailSender.send(email);
        return "/email/Result";
    }

}
