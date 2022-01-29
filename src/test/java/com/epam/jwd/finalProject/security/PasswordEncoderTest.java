package com.epam.jwd.finalProject.security;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class PasswordEncoderTest {

    @Test
    public void encoder() {
        String password = "Serov231969";
        String passwordEncoder = PasswordEncoder.getInstance().encoder(password);
        final byte[] passwordDecoder = passwordEncoder
                .getBytes(StandardCharsets.UTF_8);
        boolean resultCheck= PasswordEncoder.getInstance().checkPassword(password.getBytes(StandardCharsets.UTF_8),passwordDecoder);
                assertEquals(resultCheck, true);
    }

    @Test
    public void checkPassword() {
    }

    @Test
    public void protectFromTimingAttack() {
    }
}