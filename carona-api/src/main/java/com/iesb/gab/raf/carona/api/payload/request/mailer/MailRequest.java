package com.iesb.gab.raf.carona.api.payload.request.mailer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class MailRequest {

    private String to;
    private String recipientName;
    private String from;
    private String cc = null;
    private String Bcc = null;
    private String subject;
    private String content;
    private Set<Object> attachments = new HashSet<>();
    private Map<String, Object> model = new HashMap<>();
}
