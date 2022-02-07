package com.epam.jwd.finalProject.mail;

import org.junit.Test;

import static org.junit.Assert.*;

public class MailSenderTest {

    @Test
    public void send() {
        MailSender mailSender = new MailSender();
        mailSender.send("Password recovery code!", "Enter this code in the appropriate field on the website " +
                "to recover the password from your personal account. Code:269618", "daniils3rov@yandex.by");
    }
}