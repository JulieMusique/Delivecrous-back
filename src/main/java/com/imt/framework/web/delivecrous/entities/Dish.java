package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue //Primary Key
    private long id;
    private String title;
    private String description;
    //TODO : Changer ca après
    private Set<String> categories;
    private int price;
    private String image;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //jointure -> renseigner
    ///FetchType.EAGER charge par défaut
    private List<Ingredient> ingredientList;
    private Set<String> allergenList;

    public Dish(){
        ingredientList = new ArrayList<>();
        allergenList = new HashSet<>();
        categories = new HashSet<>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public Set<String> getAllergenList() {
        return allergenList;
    }

    public void setAllergenList(Set<String> allergenList) {
        this.allergenList = allergenList;
    }

}