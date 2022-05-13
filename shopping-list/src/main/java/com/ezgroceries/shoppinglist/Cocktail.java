package com.ezgroceries.shoppinglist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Cocktail {

    private final UUID cocktailId;
    private final String name;
    private final String glass;
    private final String instructions;
    private final String image;
    private final List<String> ingredients;

}
