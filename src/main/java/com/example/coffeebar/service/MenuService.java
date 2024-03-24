package com.example.coffeebar.service;

import com.example.coffeebar.entity.Client;
import com.example.coffeebar.entity.Desert;
import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.repository.DesertRepository;
import com.example.coffeebar.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MenuService {

    private final DrinkRepository drinkRepository;

    private final DesertRepository desertRepository;
@Autowired
    public MenuService(DrinkRepository drinkRepository, DesertRepository desertRepository) {
        this.drinkRepository = drinkRepository;
        this.desertRepository = desertRepository;
    }


    public List<Drink> getAllDrinks() {
        return drinkRepository.findAllDrinks();
    }

    public List<Desert> getAllDeserts() {
        return desertRepository.findAllDeserts();
    }

    public void addDrink(Drink drink) {
        drinkRepository.save(drink);
    }

    public void addDessert(Desert dessert) {
        desertRepository.save(dessert);
    }

    public void saveDrink(Drink drink) {
        if (drink != null) {
            drinkRepository.save(drink);
        }
    }

    public void saveDesert(Desert desert) {
        if (desert != null) {
            desertRepository.save(desert);
        }
    }

    public Drink findByIdDrink(Long idDrink) {
        return drinkRepository.findById(idDrink).orElse(new Drink());
    }

    public Desert findByIdDesert(Long idDesert) {
        return desertRepository.findById(idDesert).orElse(new Desert());
    }

    public void deleteByIdDrink(Long idDrink) {
        drinkRepository.deleteById(idDrink);
    }
    public void deleteByIdDesert(Long idDesert) {
        desertRepository.deleteById(idDesert);
    }


    public List<Drink> findDrinksByNameUa(String name) {
        return drinkRepository.findByNameUa(name);

    }

    public List<Drink> findDrinksByNameEN(String name) {
        return drinkRepository.findByNameEn(name);

    }



    public List<Desert> findDesertByNameUa(String name) {
        return desertRepository.findByNameUa(name);

    }

    public List<Desert> findDesertByNameEN(String name) {
        return desertRepository.findByNameEn(name);

    }
    public Drink getDrinkById(Long idDrink) {
        return drinkRepository.findDrinkByIdDrink(idDrink).orElse(null);
    }
    public Desert getDesertById(Long idDesert) {
        return desertRepository.findDesertByIdDesert(idDesert).orElse(null);
    }

}
