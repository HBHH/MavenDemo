package org.andy.shop.service;

import org.andy.shop.model.Email;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by CIFPAY on 2016/1/22.
 */
public interface MailService {

    public void sendMail(Email email) throws MessagingException, IOException;

    public void sendMailBySynchronizationMode(Email email) throws MessagingException, IOException;

    public void sendMailByAsynchronousMode(final Email email);
}
