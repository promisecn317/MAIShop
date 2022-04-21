package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.VerifyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class VerifyEmailService {

    private JavaMailSenderImpl mailSender;
    @Autowired
    public void setMailSender(JavaMailSenderImpl mailSender){
        this.mailSender = mailSender;
    }

    public String generateCode() {
        String sources = "0123456789";

        Random rand = new Random();
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < 6; j++)
        {
            result.append(sources.charAt(rand.nextInt(9)));
        }

        return String.valueOf(result);
    }

    public void sendVerifyEmail(VerifyEmail info) {
        try {
            SimpleMailMessage verify = new SimpleMailMessage();
            info.setFrom("yaokaize@126.com");
            String title = "[Mai Platform] Please Verify Your Email";
            info.setSubject(title);
            String content = "Hi! \n"
                    + "\nPlease verify your email using your unique code below. \n"
                    + "Code: " + info.getVerifyCode()
                    + "\n\nThanks, \n"
                    + "The Mai Team";
            info.setText(content);

            verify.setFrom(info.getFrom());
            verify.setTo(info.getTo());
            verify.setSubject(info.getSubject());
            verify.setText(info.getText());
            mailSender.send(verify);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

