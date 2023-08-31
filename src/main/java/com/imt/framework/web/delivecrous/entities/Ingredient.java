package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue //Primary Key
    private long idIngredient;
    private String name;
    private int calorie;
    private Set<String> categories;
    private Set<String> allergenList;

    public Ingredient(){
        categories = new HashSet<>();
        allergenList = new HashSet<>();
    }

    public long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<String> getAllergenList() {
        return allergenList;
    }

    public void setAllergenList(Set<String> allergenList) {
        this.allergenList = allergenList;
    }

}
