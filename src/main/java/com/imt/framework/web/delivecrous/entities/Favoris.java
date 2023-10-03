package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.*;

//La table Favorite permet aux utilisateurs de marquer desplats comme favoris.

@Entity
@Table(name = "favoris")
public class Favoris {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "plat_id")
    private Dish plat;

    public Favoris() {
    }

    public Favoris(Users user, Dish plat) {
        this.user = user;
        this.plat = plat;
    }
    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Dish getPlat() {
        return plat;
    }

    public void setPlat(Dish plat) {
        this.plat = plat;
    }
}
