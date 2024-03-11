package com.example.coffeebar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Long idPersonal;

    private String name;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @Column(name = "address", length = 255)
    private  String address;

    @OneToMany(mappedBy = "personal")
    private Set<Order> orderSet;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToMany
    @JoinTable(
            name = "personal_graphiks",
            joinColumns = @JoinColumn(name = "personal_id"),
            inverseJoinColumns = @JoinColumn(name = "graphiks_id"))
    Set<Graphic> graphicSet;
}
