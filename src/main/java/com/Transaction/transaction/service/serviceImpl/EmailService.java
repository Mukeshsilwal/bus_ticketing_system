package com.Transaction.transaction.service.serviceImpl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Base64;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;


    public void sendEmailWithAttachment(String to, String subject, String htmlBody, byte[] pdfContent) throws IOException {

        Email from = new Email("ticketkatum5@gmail.com"); // Must be verified sender
        Email recipient = new Email(to);

        Content content = new Content("text/html", htmlBody);

        Mail mail = new Mail(from, subject, recipient, content);

        // ----- Attach the PDF -----
        Attachments attachment = new Attachments();
        attachment.setContent(Base64.getEncoder().encodeToString(pdfContent));
        attachment.setType("application/pdf");
        attachment.setFilename("ticket.pdf");
        attachment.setDisposition("attachment");

        mail.addAttachments(attachment);
        // ---------------------------

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        sg.api(request);
    }
    public void sendEmailForCancelTicket(String to, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailForOtp(String to, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
