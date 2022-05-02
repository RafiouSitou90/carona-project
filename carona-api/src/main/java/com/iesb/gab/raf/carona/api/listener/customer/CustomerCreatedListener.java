package com.iesb.gab.raf.carona.api.listener.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.event.customer.CustomerCreatedEvent;
import com.iesb.gab.raf.carona.api.payload.request.mailer.MailRequest;
import com.iesb.gab.raf.carona.api.service.mailer.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Value;
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
public class CustomerCreatedListener implements ApplicationListener<CustomerCreatedEvent> {

    private final MailService mailService;
    private final Configuration freeMarkerConfig;

    @Value("${gabraf.app.backend.api.url}")
    private String apiBackendURL;

    public CustomerCreatedListener(MailService mailService, Configuration freeMarkerConfig) {
        this.mailService = mailService;
        this.freeMarkerConfig = freeMarkerConfig;
    }

    @Override
    @Async
    public void onApplicationEvent(final CustomerCreatedEvent event) {
        Customer customer = event.getEntity();
        final String loginUrl = event.getLoginUrl();

        try {
            MailRequest mailRequest = getCustomerCreatedMailRequest(customer, loginUrl);
            mailService.sendEmail(mailRequest);
        } catch (MailException | MessagingException | IOException mailException) {
            System.out.println(mailException.getMessage());
        }
    }

    private MailRequest getCustomerCreatedMailRequest(final Customer customer, final String loginUrl) throws IOException {
        MailRequest mailRequest = new MailRequest();
        Map<String, Object> model = new HashMap<>();
        User user = customer.getLogin();
        Instant expiresAt = user.getConfirmationToken().getExpiresAt();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US)
                .withZone(ZoneId.systemDefault());

//        "http://localhost:8080/api/v1/customers/confirm/email?token=%s&loginUrl=%s",
        String url = String.format(
                "%s/api/v1/customers/confirm/email?token=%s&loginUrl=%s",
                apiBackendURL,
                user.getConfirmationToken().getToken(),
                loginUrl
        );
        String expiration = dateTimeFormatter.format(expiresAt);

        model.put("fullName", customer.getFullName() != null ? customer.getFullName() : user.getUsername());
        model.put("username", user.getUsername());
        model.put("email", user.getEmail());
        model.put("expiration", expiration);
        model.put("url", url);

        try {
            Template emailTemplate = freeMarkerConfig.getTemplate("customer/confirmation-email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model);

            mailRequest.setTo(user.getEmail());
            mailRequest.setSubject("New Account created confirmation email.");
            mailRequest.setContent(html);
            mailRequest.setModel(model);
        } catch (IOException | TemplateException e) {
            System.out.println(e.getMessage());
        }

        return mailRequest;
    }
}
