package com.carvalho.emailservice.adapters;

public interface EmailSenderGatewar {

    void sendEmail(String to, String subject, String body);

}
