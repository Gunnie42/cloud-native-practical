package com.ezgroceries.shoppinglist.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {
}
