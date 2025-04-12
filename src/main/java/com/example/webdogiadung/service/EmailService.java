//package com.example.webdogiadung.service;
//
//import com.mailersend.sdk.MailerSend;
//import com.mailersend.sdk.MailerSendResponse;
//import com.mailersend.sdk.emails.Email;
//import com.mailersend.sdk.exceptions.MailerSendException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//import web.examination.CostumeForRent.dto.request.EmailRequest;
//import web.examination.CostumeForRent.properties.MailerProperties;
//import web.examination.CostumeForRent.service.interfa.EmailServiceInterface;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class EmailService implements EmailServiceInterface {
//    private final MailerProperties mailerProperties;
//
//    @KafkaListener(topics = "email_message",groupId = "my-group")
//    public void sendEmail(@Payload EmailRequest emailRequest) {
//        Email email = new Email();
//        email.setFrom("Nguyen Tuan Anh", "anhnt204@trial-zxk54v8zrzxljy6v.mlsender.net");
//        email.addRecipient("nguyentuananh", "anhnt204.dev@gmail.com");
//        email.setTemplateId("pq3enl6qzm742vwr");
//        email.setSubject("cconcac");
//        MailerSend ms = new MailerSend();
//        ms.setToken(mailerProperties.getApiKey());
//        try {
//            MailerSendResponse response = ms.emails().send(email);
//            System.out.println(response.messageId);
//        } catch (MailerSendException e) {
//
//            e.printStackTrace();
//        }
//    }
//
//}
