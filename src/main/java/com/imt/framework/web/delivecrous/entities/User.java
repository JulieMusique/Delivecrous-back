// Source code is decompiled from a .class file using FernFlower decompiler.
package com.imt.framework.web.delivecrous.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@JsonPropertyOrder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nom")
    private String lastName;

    @Column(name = "prénom")
    private String firstName;

    private String email;
    private String phone;
    private String adresse;
    private String login;
    private String password;

    @Column(name = "solde_carte_crous")
    private Double soldeCarteCrous;

    public Double getSoldeCarteCrous() {
        return soldeCarteCrous;
    }

    public void setSoldeCarteCrous(Double soldeCarteCrous) {
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

    public boolean verifyPassword(String hashedPassword, String plainPassword) {
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
            return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null)
            return false;
        return adresse.equals(user.adresse);
    }

}