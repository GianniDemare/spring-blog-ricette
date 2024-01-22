package org.learning.springblogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "categorie")
public class Categoria {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String name;
    @OneToMany
    private List<Ricetta> ricette;

    // GETTER E SETTER


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ricetta> getRicette() {
        return ricette;
    }

    public void setRicette(List<Ricetta> ricette) {
        this.ricette = ricette;
    }
}
