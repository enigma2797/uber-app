package com.project.uber.services.impl;

import com.project.uber.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geolatte.geom.Simple;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String mailTo, String subject, String body) {

        try
        {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailTo);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
            log.info("email has been sent successfully");
        }
        catch (Exception e)
        {
         log.info("exception occurred during sending mail : {}",e.getMessage());
        }

    }

    @Override
    public void sendEmail(String[] mailTo, String subject, String body) {
        try
        {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailTo);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
            log.info("email has been sent successfully");
        }
        catch (Exception e)
        {
            log.info("exception occurred during sending mail : {}",e.getMessage());
        }
    }
}
