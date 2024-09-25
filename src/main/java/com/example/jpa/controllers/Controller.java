package com.example.jpa.controllers;

import com.example.jpa.models.SaleRequest;
import com.example.jpa.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private SaleService saleService;

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("sales", saleService.findAll());
        model.addAttribute("request", new SaleRequest());
        model.addAttribute("workername");
        return "home";
    }

    @PostMapping("/")
    public String form(@RequestParam String workername, Model model) {
        model.addAttribute("sales", saleService.findAll());
        model.addAttribute("request", new SaleRequest());
        model.addAttribute("workername", workername);
        return "salesweb";
    }

    @GetMapping("/home")
    public String initHome() {
        return "home";
    }

    @GetMapping("/salesweb")
    public String initSalesweb(@ModelAttribute SaleRequest request, Model model, @ModelAttribute String workername) {
        model.addAttribute("sales", saleService.findAll());
        model.addAttribute("request", new SaleRequest());
        model.addAttribute("workername", workername);
        return "salesweb";
    }

    @PostMapping("/salesweb")
    public String createSale(@ModelAttribute SaleRequest request, Model model) {
        model.addAttribute("sales", saleService.findAll());
        model.addAttribute("request", new SaleRequest());
        saleService.createSale(request);
        return "salesweb";
    }
}
