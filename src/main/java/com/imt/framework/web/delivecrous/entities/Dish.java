package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue
    private long idDish;
    private String title;
    private String description;
    private Set<String> categories;
    private double price;
    private String imagePath;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ingredient> ingredientList;
    private Set<String> allergenList;

    public Dish(){
        ingredientList = new ArrayList<>();
        allergenList = new HashSet<>();
        categories = new HashSet<>();
    }
    public long getIdDish() {
        return idDish;
    }

    public void setIdDish(long idDish) {
        this.idDish = idDish;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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