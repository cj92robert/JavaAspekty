package com.example.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class MovieAspect {
    private JavaMailSender sender;

    @Autowired
    public MovieAspect(JavaMailSender sender) {
        this.sender = sender;
    }

    @After("execution(* com.example.demo.MovieController.*(..))")
    private void sendEmail() {
        System.out.println("aspect");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setTo("cj92robert@gmail.com");
            helper.setText("Numer ip użytkownika "+request.getRemoteAddr());
            helper.setSubject("Użycie strony");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

}
