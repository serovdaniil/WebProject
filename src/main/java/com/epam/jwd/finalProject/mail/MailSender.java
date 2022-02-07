package com.epam.jwd.finalProject.mail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private static final Logger LOG = LogManager.getLogger(MailSender.class);
    private static final String SEND_EMAIL_EXCEPTION = "Exception occurred sending email! ";
    private final String login;
    private final String password;
    private final Properties properties;

    public MailSender() {
        this.login = MailSenderConfig.email;
        this.password = MailSenderConfig.password;
        properties = new Properties();
        properties.put(MailSenderConfig.AUTH, MailSenderConfig.auth);
        properties.put(MailSenderConfig.START_TLS, MailSenderConfig.startTls);
        properties.put(MailSenderConfig.HOST, MailSenderConfig.host);
        properties.put(MailSenderConfig.PORT, MailSenderConfig.port);
    }

    public void send(String subject, String text, String recipientEmail) {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return  new PasswordAuthentication(login, password);
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        try {
            Message message = new MimeMessage(session);
            InternetAddress address = new InternetAddress(login);
            message.setFrom(address);
            InternetAddress[] internetAddresses = InternetAddress.parse(recipientEmail);
            message.setRecipients(Message.RecipientType.TO, internetAddresses);
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);

        }  catch (MessagingException e) {
            LOG.error(SEND_EMAIL_EXCEPTION, e);
        }
    }
}
