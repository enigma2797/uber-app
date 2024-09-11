package com.project.uber.services;

public interface EmailSenderService {

    public void sendEmail(String mailTo, String subject, String body);
    public void sendEmail(String[] mailTo, String subject, String body);
}
