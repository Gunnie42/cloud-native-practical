package com.ezgroceries.shoppinglist.service;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CocktailDBResponse {
    private List<DrinkResource> drinks;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strImageSource;
        private List<String> ingredients;

        DrinkResource(){
            ingredients = new ArrayList<>();
        }

        @JsonAnySetter
        public void add(String property, String value){
            if (property.startsWith("strIngredient") && value != null) {
                ingredients.add(value);
            }
        }

    }
}
