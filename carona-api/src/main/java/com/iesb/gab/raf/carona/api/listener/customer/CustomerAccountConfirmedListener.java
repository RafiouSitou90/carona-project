package com.iesb.gab.raf.carona.api.listener.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.event.customer.CustomerAccountConfirmedEvent;
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
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomerAccountConfirmedListener implements ApplicationListener<CustomerAccountConfirmedEvent> {

    private final MailService mailService;
    private final Configuration freeMarkerConfig;

    @Async
    @Override
    public void onApplicationEvent(CustomerAccountConfirmedEvent event) {
        final Customer customer = event.getCustomer();
        final String loginUrl = event.getLoginUrl();

        try {
            MailRequest mailRequest = getCustomerAccountConfirmedMailRequest(customer, loginUrl);
            mailService.sendEmail(mailRequest);
        } catch (MailException | MessagingException | IOException mailException) {
            System.out.println(mailException.getMessage());
        }
    }

    private MailRequest getCustomerAccountConfirmedMailRequest(final Customer customer, String loginUrl) throws IOException {
        MailRequest mailRequest = new MailRequest();
        Map<String, Object> model = new HashMap<>();
        final User user = customer.getLogin();

        // "http://localhost:3000/auth/login"
        String url = String.format("%s", loginUrl);

        model.put("fullName", customer.getFullName() != null ? customer.getFullName() : user.getUsername());
        model.put("url", url);

        try {
            Template emailTemplate = freeMarkerConfig.getTemplate("customer/account-confirmed.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model);

            mailRequest.setTo(user.getEmail());
            mailRequest.setSubject("Customer Account successfully confirmed");
            mailRequest.setContent(html);
            mailRequest.setModel(model);
        } catch (IOException | TemplateException e) {
            System.out.println(e.getMessage());
        }

        return mailRequest;
    }
}
