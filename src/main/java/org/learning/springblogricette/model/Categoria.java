package org.learning.springblogricette.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categoria {
    private String id;
    private String name;
}
