package com.example.coffeebar.entity;

import jakarta.persistence.*;

@Entity
public class Deserts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id_desert")
    private int idDesert;

    public int getIdDesert() {
        return idDesert;
    }

    public void setIdDesert(int idDesert) {
        this.idDesert = idDesert;
    }

    @Basic
    @Column(name = "name_ua")
    private String nameUa;

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    @Basic
    @Column(name = "name_en")
    private String nameEn;

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "price")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deserts deserts = (Deserts) o;

        if (idDesert != deserts.idDesert) return false;
        if (price != deserts.price) return false;
        if (nameUa != null ? !nameUa.equals(deserts.nameUa) : deserts.nameUa != null) return false;
        if (nameEn != null ? !nameEn.equals(deserts.nameEn) : deserts.nameEn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDesert;
        result = 31 * result + (nameUa != null ? nameUa.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
