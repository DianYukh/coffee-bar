package com.example.coffeebar.repository;


import com.example.coffeebar.entity.Desert;
import com.example.coffeebar.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
