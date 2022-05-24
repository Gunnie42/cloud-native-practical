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
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController ( CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public ResponseEntity<List<CocktailResource>> get(@RequestParam String search) {
        return ResponseEntity.ok(cocktailService.searchCocktails(search));
    }

}