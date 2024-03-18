package com.example.coffeebar.repository;

import com.example.coffeebar.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByNameUa(String name);
    List<Drink> findByNameEn(String name);
 List<Drink> findByPrice(BigDecimal price);


}
