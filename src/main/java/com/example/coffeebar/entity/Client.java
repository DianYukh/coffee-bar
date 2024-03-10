package com.example.coffeebar.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Clients {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id_clients")
    private int idClients;

    public int getIdClients() {
        return idClients;
    }

    public void setIdClients(int idClients) {
        this.idClients = idClients;
    }

    @Basic
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "day_of_birth")
    private Date dayOfBirth;

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Basic
    @Column(name = "phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "sale")
    private Integer sale;

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clients clients = (Clients) o;

        if (idClients != clients.idClients) return false;
        if (name != null ? !name.equals(clients.name) : clients.name != null) return false;
        if (dayOfBirth != null ? !dayOfBirth.equals(clients.dayOfBirth) : clients.dayOfBirth != null) return false;
        if (phone != null ? !phone.equals(clients.phone) : clients.phone != null) return false;
        if (address != null ? !address.equals(clients.address) : clients.address != null) return false;
        if (sale != null ? !sale.equals(clients.sale) : clients.sale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClients;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dayOfBirth != null ? dayOfBirth.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }
}
