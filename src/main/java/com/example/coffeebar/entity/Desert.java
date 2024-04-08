package com.example.coffeebar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
@Table(name = "deserts")
public class Desert {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_desert", nullable = false)
    private Long idDesert;

    @Basic
    @Column(name = "name_ua", nullable = true, length = 255)
    @NotEmpty(message = "Поле nameUa не може бути порожнім")
    private String nameEn;


    @Basic
    @Column(name = "name_en", nullable = true, length = 255)
    @NotEmpty(message = "Поле nameUa не може бути порожнім")
    private String nameUa;

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "desertSet")
    Set<Order> orderSet;


    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;



}
