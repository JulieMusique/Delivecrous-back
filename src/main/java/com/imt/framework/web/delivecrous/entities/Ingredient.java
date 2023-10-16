package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
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

    /**
     * Override equals beacause, need to compare all params in ingredient without id who is unique
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Ingredient ingredient = (Ingredient) o;
        if(this.name.equals(ingredient.name))
            return false;
        if(this.calorie != ingredient.calorie)
            return false;
        if(!this.categories.containsAll(ingredient.categories))
            return false;
        if(!this.allergenList.containsAll(ingredient.allergenList))
            return false;
        return true;
    }

}
