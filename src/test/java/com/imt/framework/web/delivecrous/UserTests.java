package com.imt.framework.web.delivecrous;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import com.imt.framework.web.delivecrous.entities.Users;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.junit.jupiter.api.Test;

public class UserTests {

    private Users user;

    @BeforeEach
    public void setUp() {
        user = new Users();
    }

    @Test
    public void testGetFirstName() {
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }


    @Test
    public void testGetEmail() {
        Users user = new Users();
        user.setEmail("john@example.com");
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testGetPhone() {
        Users user = new Users();
        user.setPhone("1234567890");
        assertEquals("1234567890", user.getPhone());
    }

    @Test
    public void testGetLogin() {
        Users user = new Users();
        user.setLogin("john_doe");
        assertEquals("john_doe", user.getLogin());
    }

    @Test
    public void testSetPassword() {
        String plainPassword = "securePassword123";

        Users user = new Users();
        user.setPassword(plainPassword);

        // Manually verify the hashed password
        Argon2 argon2 = Argon2Factory.create();
        boolean verified = argon2.verify(user.getPassword(), plainPassword);
        assertTrue(verified);
    }

    @Test
    public void testVerifyPassword() {
        String plainPassword = "securePassword123";

        Users user = new Users();
        user.setPassword(plainPassword);

        boolean isValid = user.verifyPassword(user.getPassword(), plainPassword);
        assertTrue(isValid);
    }

    @Test
    public void testGetAdresse() {
        Users user = new Users();
        user.setAdresse("123 Main St");
        assertEquals("123 Main St", user.getAdresse());
    }

    @Test
    public void testGetId() {
        Users user = new Users();
        user.setIdUser(1L);
        assertEquals(1L, user.getIdUser());
    }

    @Test
    public void testGetSoldeCarteCrous() {
        double solde = 100.0; // Valeur de solde fictive pour le test
        user.setSoldeCarteCrous(solde);
        assertEquals(solde, user.getSoldeCarteCrous());
    }

    @Test
    public void testSetSoldeCarteCrous() {
        double solde = 200.0; // Nouvelle valeur de solde fictive pour le test
        user.setSoldeCarteCrous(solde);
        assertEquals(solde, user.getSoldeCarteCrous());
    }
}

