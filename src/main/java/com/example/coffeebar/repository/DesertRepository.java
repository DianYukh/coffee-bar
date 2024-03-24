package com.example.coffeebar.repository;


import com.example.coffeebar.entity.Desert;
import com.example.coffeebar.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DesertRepository extends JpaRepository<Desert, Long> {
List<Desert> findByNameUa(String name);
List<Desert> findByNameEn(String name);
   List<Desert> findByPrice(BigDecimal price);

   Optional<Desert> findDesertByIdDesert(Long id);

   @Query("from Desert")
   List<Desert> findAllDeserts();


}
