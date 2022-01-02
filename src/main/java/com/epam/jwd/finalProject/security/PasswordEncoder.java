package com.epam.jwd.finalProject.security;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

/**
 * @author Daniil Serov
 */
public class PasswordEncoder {
    /**
     * Get bytes password
     */
    private static final byte[] DUMMY_PASSWORD = "password".getBytes(StandardCharsets.UTF_8);

    /**
     * BCrypt for password
     */
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifier;

    /**
     * Constructor - creating a new object
     *
     * @param hasher   dao for this service
     * @param verifier dao for this service
     */
    public PasswordEncoder(BCrypt.Hasher hasher, BCrypt.Verifyer verifier) {
        this.hasher = hasher;
        this.verifier = verifier;
    }

    public String encoder(String password) {
        final char[] rawPassword = password.toCharArray();
        return hasher.hashToString(MIN_COST, rawPassword);
    }

    public boolean checkPassword(byte[] enteredPassword, byte[] hashedPassword) {
        return verifier.verify(enteredPassword, hashedPassword).verified;
    }

    /**
     * Password
     */
    public void protectFromTimingAttack(byte[] enteredPassword) {
        verifier.verify(enteredPassword, DUMMY_PASSWORD);
    }

    public static PasswordEncoder getInstance() {
        return PasswordEncoder.Holder.INSTANCE;
    }

    private static class Holder {
        public static final PasswordEncoder INSTANCE = new PasswordEncoder(BCrypt.withDefaults(), BCrypt.verifyer());
    }
}
