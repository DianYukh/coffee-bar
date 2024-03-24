package com.example.coffeebar.repository;

import com.example.coffeebar.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByNameUa(String name);
    List<Drink> findByNameEn(String name);
 List<Drink> findByPrice(BigDecimal price);

 Optional<Drink> findDrinkByIdDrink(Long id);

@Query("from Drink")
 List<Drink> findAllDrinks();


}
