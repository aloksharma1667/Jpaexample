package com.jpa.test;

import com.jpa.entities.User;
import com.jpa.test.dao.UserRepo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jpa.test.JpaExampleApplication;
import jakarta.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.jpa.entities")
@ComponentScan
@EnableTransactionManagement
public class JpaExampleApplication {
    @Autowired
    private EmailSenderService1 emailSenderService;
    
    @Autowired
    private UserRepo sentMailRepository1;


    private String[] recipients = {
            "alok.sharma@cotmac.io",
            "aloksharma1667@gmail.com",
            "engineeralok05@gmail.com"
    };
    private String body = "Mail demo testing..............";
    private String subject = "demo testing";
    private String attachment = "D:/Khandhari report/file.xlsx";
    private int recipientIndex = 0;

    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void sendEmailTask() {
        // Start the initial email sending
        sendNextEmail();
    }

    @Scheduled(fixedDelay = 300)
    private <S> void sendNextEmail() {
        if (recipientIndex < recipients.length) {
            try {
                String recipient = recipients[recipientIndex];
                emailSenderService.sendMailWithAttachment(recipient, body, subject, attachment);

                // Save the email details to the database
                User sentMail = new User();
                sentMail.setRecipient(recipient);
                sentMail.setSubject(subject);
                sentMail.setSentDateTime(LocalDateTime.now());
                sentMailRepository1.save(sentMail);

                recipientIndex++;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            recipientIndex = 0;  // Reset recipient index to start sending emails from the beginning
        }
    }
}