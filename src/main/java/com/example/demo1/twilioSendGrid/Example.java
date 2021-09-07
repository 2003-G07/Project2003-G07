package com.example.demo1.twilioSendGrid;// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {

        Email from = new Email("henke_e_96@hotmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("henke_e_96@hotmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (IOException ex) {

            throw ex;
        }
    }
}