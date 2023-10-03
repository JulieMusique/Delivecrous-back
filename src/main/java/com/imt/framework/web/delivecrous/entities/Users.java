package com.imt.framework.web.delivecrous.entities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue
    private Long idUser;

    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String adresse;
    private String login;
    private String password;
    private double soldeCarteCrous;

    public Users() {
    }

    public double getSoldeCarteCrous() {
        return soldeCarteCrous;
    }

    public void setSoldeCarteCrous(double soldeCarteCrous) {
        this.soldeCarteCrous = soldeCarteCrous;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String plainPassword) {
        Argon2 argon2 = Argon2Factory.create();
        String hash = argon2.hash(10, 65536, 1, plainPassword);
        this.password = hash;
    }

    public static boolean verifyPassword(String hashedPassword, String plainPassword) {
        Argon2 argon2 = Argon2Factory.create();
        boolean verified = argon2.verify(hashedPassword, plainPassword);
        return verified;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long id) {
        this.idUser = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Users [idUser=" + idUser + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
                + ", phone=" + phone + ", adresse=" + adresse + ", login=" + login + ", password=" + password
                + ", soldeCarteCrous=" + soldeCarteCrous + "]";
    }

}