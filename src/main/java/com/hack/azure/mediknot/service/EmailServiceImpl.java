package com.hack.azure.mediknot.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    private SendGrid sendGridClient;

    public EmailServiceImpl(SendGrid sendGridClient) {
        this.sendGridClient = sendGridClient;
    }

    @Override
    public void sendText(String from, String to, String subject, String body) {
        sendEmail(from, to, subject, new Content("text/plain", body));
    }

    @Override
    public void sendHTML(String from, String to, String subject, String body) {
        sendEmail(from, to, subject, new Content("text/html", body));
    }

    private void sendEmail(String from, String to, String subject, Content content) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        mail.setReplyTo(new Email(from));
        Request request = new Request();
        sendGridClient.initializeSendGrid("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");
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGridClient.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}