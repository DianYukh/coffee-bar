package com.example.coffeebar.entity;

import jakarta.persistence.*;

@Entity
public class Graphiks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id_graphiks")
    private int idGraphiks;

    public int getIdGraphiks() {
        return idGraphiks;
    }

    public void setIdGraphiks(int idGraphiks) {
        this.idGraphiks = idGraphiks;
    }

    @Basic
    @Column(name = "day_of_work")
    private int dayOfWork;

    public int getDayOfWork() {
        return dayOfWork;
    }

    public void setDayOfWork(int dayOfWork) {
        this.dayOfWork = dayOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graphiks graphiks = (Graphiks) o;

        if (idGraphiks != graphiks.idGraphiks) return false;
        if (dayOfWork != graphiks.dayOfWork) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGraphiks;
        result = 31 * result + dayOfWork;
        return result;
    }
}
