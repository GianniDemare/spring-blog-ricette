package org.learning.springblogricette.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ricette")
public class Ricetta {

    //ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String ingredients;
    private String image;
    private Integer preparationTime;
    private Integer numberOfPortions;
    private String recipeText;

    @ManyToMany
    private List<Categoria> categories;

    // GETTER E SETTER


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getNumberOfPortions() {
        return numberOfPortions;
    }

    public void setNumberOfPortions(Integer numberOfPortions) {
        this.numberOfPortions = numberOfPortions;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public List<Categoria> getCategories() {
        return categories;
    }

    public void setCategories(List<Categoria> categories) {
        this.categories = categories;
    }
}
