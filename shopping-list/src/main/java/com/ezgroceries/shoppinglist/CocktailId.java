package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class CocktailId {

    private final UUID cocktailId;

    public UUID getCocktailId() {
        return cocktailId;
    }

    @JsonCreator
    public CocktailId(UUID cocktailId){
        this.cocktailId = cocktailId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailId that = (CocktailId) o;
        return cocktailId.equals(that.cocktailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId);
    }
}
