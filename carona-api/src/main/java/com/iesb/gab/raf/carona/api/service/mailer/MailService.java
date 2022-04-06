package com.iesb.gab.raf.carona.api.service.mailer;

import com.iesb.gab.raf.carona.api.payload.request.mailer.MailRequest;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    void sendEmail(MailRequest model) throws MailException, MessagingException, UnsupportedEncodingException;

    void sendEmailWithAttachment(MailRequest model) throws MailException, MessagingException;
}
