package com.example.coffeebar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drinks", nullable = false)
    private Integer idDrink;

    @Basic
    @Column(name = "name_ua", nullable = true, length = 255)
    private String nameUa;

    @Basic
    @Column(name = "name_en", nullable = true, length = 255)
    private String nameEn;

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "drinkSet")
    Set<Order> orderSet;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public Drink(Integer idDrink, String nameUa, String nameEn, BigDecimal price, Set<Order> orderSet) {
        this.idDrink = idDrink;
        this.nameUa = nameUa;
        this.nameEn = nameEn;
        this.price = price;
        this.orderSet = orderSet;
    }
}
