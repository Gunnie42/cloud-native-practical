package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.CocktailResource;
import com.ezgroceries.shoppinglist.persistence.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.CocktailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient cocktailDBClient;

    private final List<CocktailResource> cocktails = Arrays.asList(
            new CocktailResource(
            UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
            "Cocktail glass",
            "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
            Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
            new CocktailResource(
            UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
            "Cocktail glass",
            "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
            "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
            Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));

    public CocktailService(
            CocktailRepository cocktailRepository,
            CocktailDBClient cocktailDBClient) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailDBClient = cocktailDBClient;
    }

    public CocktailResource getCocktail(UUID cocktailId) {
        return cocktails.stream()
                .filter(cocktail -> cocktailId.equals(cocktail.getCocktailId()))
                .findFirst()
                .orElse(null);
    }

    public List<String> getIngredients(UUID cocktailId) {
        CocktailResource cocktail = getCocktail(cocktailId);
        return cocktail.getIngredients();
    }

    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        // Get all the iDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        // Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        // Stream over all the drinks, map them to the existing ones, persist a new one if not exisiting
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setId(UUID.randomUUID());
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                newCocktailEntity.setGlass(drinkResource.getStrGlass());
                newCocktailEntity.setImage(drinkResource.getStrImageSource());
                newCocktailEntity.setInstructions(drinkResource.getStrInstructions());
                newCocktailEntity.setIngredients(new HashSet<>(drinkResource.getIngredients()));
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }


    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrImageSource(), drinkResource.getIngredients())).collect(Collectors.toList());
    }

    public List<CocktailResource> searchCocktails(String search) {
        List<CocktailDBResponse.DrinkResource> drinks = cocktailDBClient.searchCocktails(search).getDrinks();
        return mergeCocktails(drinks);
    }
}
