package com.iesb.gab.raf.carona.api.listener.user;

import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.event.user.UserPasswordUpgradedEvent;

import com.iesb.gab.raf.carona.api.payload.request.mailer.MailRequest;
import com.iesb.gab.raf.carona.api.service.mailer.MailService;

import freemarker.template.Configuration;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@AllArgsConstructor
public class UserPasswordUpgradedListener implements ApplicationListener<UserPasswordUpgradedEvent> {
    private final MailService mailService;
    private final Configuration freeMarkerConfig;

    @Async
    @Override
    public void onApplicationEvent(UserPasswordUpgradedEvent event) {
        final ResetPasswordToken resetPasswordToken = event.getEntity();
        final String loginUrl = event.getLoginUrl();

        try {
            MailRequest mailRequest = getUserPasswordUpgradedMailRequest(resetPasswordToken, loginUrl);
            mailService.sendEmail(mailRequest);
        } catch (MailException | MessagingException | IOException mailException) {
            System.out.println(mailException.getMessage());
        }
    }

    private MailRequest getUserPasswordUpgradedMailRequest(final ResetPasswordToken resetPasswordToken, String loginUrl)
            throws IOException {

        MailRequest mailRequest = new MailRequest();
        Map<String, Object> model = new HashMap<>();
        final User user = resetPasswordToken.getUser();

        // "http://localhost:3000/auth/login"
        String url = String.format("%s", loginUrl);

        model.put("fullName", user.getCustomer().getFullName() != null ? user.getCustomer().getFullName() : user.getUsername());
        model.put("url", url);

        try {
            Template emailTemplate = freeMarkerConfig.getTemplate("user/password-upgraded.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model);

            mailRequest.setTo(user.getEmail());
            mailRequest.setSubject("User Password Upgraded");
            mailRequest.setContent(html);
            mailRequest.setModel(model);
        } catch (IOException | TemplateException e) {
            System.out.println(e.getMessage());
        }

        return mailRequest;
    }
}
