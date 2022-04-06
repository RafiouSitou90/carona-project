package com.iesb.gab.raf.carona.api.service.mailer;

import com.iesb.gab.raf.carona.api.payload.request.mailer.MailRequest;

import lombok.AllArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(MailRequest model) throws MailException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

        try {
            if (model.getRecipientName() != null) {
                helper.setTo(new InternetAddress(model.getTo(), model.getRecipientName()));
            } else {
                helper.setTo(model.getTo());
            }

            helper.setFrom(new InternetAddress("no-reply@carona.gabraf.com.br", "Carona App GabRaf"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (model.getCc() != null) {
            helper.setCc(model.getCc());
        }

        if (model.getBcc() != null) {
            helper.setBcc(model.getBcc());
        }

        helper.setSubject(model.getSubject());
        helper.setText(model.getContent(), true);

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailWithAttachment(MailRequest model) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // @TODO: Make a real implementation of sendEmailWithAttachement with real attachement files

        helper.setFrom("no-reply@carona.gabraf.com.br");
        helper.setTo("demo@example.com.br");
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(Objects.requireNonNull(classPathResource.getFilename()), classPathResource);

        javaMailSender.send(mimeMessage);
    }
}
