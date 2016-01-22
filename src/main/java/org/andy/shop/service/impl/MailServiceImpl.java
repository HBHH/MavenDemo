package org.andy.shop.service.impl;

import org.andy.shop.model.Email;
import org.andy.shop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by CIFPAY on 2016/1/22.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Value("${username}")
    private String username ;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器

    public void sendMail(Email email) throws MessagingException, IOException {
        /*if (email.getAddress().length > 5) {// 收件人大于5封时，采用异步发送
            sendMailByAsynchronousMode(email);
            this.message.append("收件人过多，正在采用异步方式发送...<br/>");
        } else {*/
        email.setFromAddress(username);
        sendMailBySynchronizationMode(email);
        //this.message.append("正在同步方式发送邮件...<br/>");
        //}
    }

    public void sendMailBySynchronizationMode(Email email)
            throws MessagingException, IOException {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");
        helper.setFrom(email.getFromAddress());// 发件人
        helper.setTo(email.getToAddress());// 收件人
        helper.setReplyTo(email.getFromAddress());// 回复到
        helper.setSubject(email.getSubject());// 邮件主题
        helper.setText(email.getContent(), true);// true表示设定html格式
        mailSender.send(mime);
    }

    public void sendMailByAsynchronousMode(final Email email) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMailBySynchronizationMode(email);
                } catch (Exception e) {

                }
            }
        });
    }
}
