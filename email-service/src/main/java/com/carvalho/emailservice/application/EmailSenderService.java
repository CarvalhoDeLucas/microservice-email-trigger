package com.carvalho.emailservice.application;

import com.carvalho.emailservice.adapters.EmailSenderGatewar;
import com.carvalho.emailservice.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGatewar emailSenderGatewar;

    @Autowired
    public EmailSenderService(EmailSenderGatewar emailGatewar) {
        this.emailSenderGatewar = emailGatewar;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {

    }

}
