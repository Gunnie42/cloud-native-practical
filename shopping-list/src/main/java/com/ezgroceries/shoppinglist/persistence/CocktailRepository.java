package com.ezgroceries.shoppinglist.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {
    List<CocktailEntity> findByIdDrinkIn(List<String> ids);
}
