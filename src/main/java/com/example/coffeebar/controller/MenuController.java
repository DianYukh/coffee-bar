package com.example.coffeebar.controller;

import com.example.coffeebar.entity.Desert;
import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.entity.Image;
import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.service.ImageService;
import com.example.coffeebar.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MenuController {

    private MenuService menuService;
    private ImageService imageService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("drinks", menuService.getAllDrinks());
        model.addAttribute("deserts", menuService.getAllDeserts());
        return "menu";
    }

    @GetMapping("/drink/add")
    public String addDrink(Model model) {
        model.addAttribute("drink", new Drink());
        return "admin/add-drink";
    }
    @PostMapping("/drink/add")
    public String addDrink(@Valid @ModelAttribute Drink drink, BindingResult bindingResult,
                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            return "admin/add-drink";
        }

        if (file != null) {
            com.example.coffeebar.entity.Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setContent(file.getBytes());
            imageService.save(image);
            drink.setImage(image);
        }

        menuService.saveDrink(drink);
        return "redirect:/menu";
    }
    @GetMapping("/desert/add")
    public String addDesert(Model model) {
        model.addAttribute("desert", new Desert());
        return "admin/add-desert";
    }

    @PostMapping("/desert/add")
    public String addDesert(@Valid @ModelAttribute Desert desert,  BindingResult bindingResult,
                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            return "admin/add-desert";
        }
        if (file != null) {
            com.example.coffeebar.entity.Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setContent(file.getBytes());
            imageService.save(image);
            desert.setImage(image);
        }

        menuService.saveDesert(desert);
        return "redirect:/menu";
    }

    @GetMapping("/drink/updateDrink/{id}")
    public String updateDrink(@PathVariable(name = "id") Long idDrink,
                              Model model) {
        Drink drinkById = menuService.findByIdDrink(idDrink);
        model.addAttribute("drink", drinkById);
        return "admin/add-drink";
    }

    @GetMapping("/desert/updateDesert/{id}")
    public String updateDesert(@PathVariable(name = "id") Long idDesert, Model model) {
        Desert desertById = menuService.findByIdDesert(idDesert);
        model.addAttribute("desert", desertById);
        return "admin/add-desert";
    }
    @GetMapping("/drink/delete/{idDrink}")
    public String deleteDrink(@PathVariable Long idDrink) {
        menuService.deleteByIdDrink(idDrink);
        return "redirect:/menu";
    }
    @GetMapping("/desert/delete/{idDesert}")
    public String deleteDesert(@PathVariable Long idDesert) {
        menuService.deleteByIdDesert(idDesert);
        return "redirect:/menu";
    }

    @GetMapping("/drink/updatePrice/{idDrink}")
    public String updatePriceDrink(@PathVariable Long idDrink, Model model) {
        Drink drinkById = menuService.findByIdDrink(idDrink);
        model.addAttribute("drink", drinkById);
        return "updateDrink-price";
    }


    @PostMapping("/drink/updatePrice/{id}")
    public String updatePriceDrink(@PathVariable Long id, @RequestParam(name = "price") BigDecimal price) {
        Drink byIdDrink = menuService.findByIdDrink(id);
        byIdDrink.setPrice(price);
        menuService.saveDrink(byIdDrink);
        return "redirect:/menu";
    }

    @PostMapping("/desert/updatePrice/{id}")
    public String updatePriceDesert(@PathVariable Long id, @RequestParam(name = "price") BigDecimal price) {
        Desert byIdDesert= menuService.findByIdDesert(id);
        byIdDesert.setPrice(price);
        menuService.saveDesert(byIdDesert);
        return "redirect:/menu";
    }


    @GetMapping("/desert/updatePrice/{idDesert}")
    public String updatePriceDesert(@PathVariable Long idDesert, Model model) {
        Desert desertById = menuService.findByIdDesert(idDesert);
        model.addAttribute("desert", desertById);
        return "updateDesert-price";
    }

    @PostMapping("/menu/list")
    public String getAllByMenu(@RequestParam(name = "selectedDrinks", required = false, defaultValue = "false") boolean selectedDrinks,
                               @RequestParam(name = "selectedDeserts", required = false, defaultValue = "false") boolean selectedDeserts,
                               @RequestParam(name = "sort", required = false) Integer num,
                               Model model) {
        List<Drink> drinks = new ArrayList<>();
        List<Desert> deserts = new ArrayList<>();

        if (selectedDrinks) {
            drinks = menuService.getAllDrinks();
        }

        if (selectedDeserts) {
            deserts = menuService.getAllDeserts();
        }

        if (num != null) {
            if (selectedDrinks) {
                switch (num) {
                    case 1:
                        Collections.sort(drinks, Comparator.comparing(Drink::getNameUa));
                        break;
                    case 2:
                        Collections.sort(drinks, Comparator.comparing(Drink::getNameEn));
                        break;
                    case 3:
                        Collections.sort(drinks, Comparator.comparing(Drink::getPrice));
                        break;
                }
            } else if (selectedDeserts) {
                switch (num) {
                    case 1:
                        Collections.sort(deserts, Comparator.comparing(Desert::getNameUa));
                        break;
                    case 2:
                        Collections.sort(deserts, Comparator.comparing(Desert::getNameEn));
                        break;
                    case 3:
                        Collections.sort(deserts, Comparator.comparing(Desert::getPrice));
                        break;
                }
            }
        }

        model.addAttribute("drinks", drinks);
        model.addAttribute("deserts", deserts);
        model.addAttribute("selectedDrinks", selectedDrinks);
        model.addAttribute("selectedDeserts", selectedDeserts);
        return "menu";
    }





}
