package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.service.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.CocktailDBResponse;
import com.ezgroceries.shoppinglist.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailDBClient cocktailDBClient;
    private final CocktailService cocktailService;

    @GetMapping
    public ResponseEntity<List<CocktailResource>> get(@RequestParam String search) {
        List<CocktailDBResponse.DrinkResource> drinks = cocktailDBClient.searchCocktails(search).getDrinks();
        return ResponseEntity.ok(cocktailService.mergeCocktails(drinks));
    }

}