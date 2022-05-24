package com.ezgroceries.shoppinglist.persistence;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
@Setter
@Getter
public class CocktailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String idDrink;
    private String name;
    private String glass;
    private String instructions;
    private String image;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

}
