package com.example.jpa.controllers;

import com.example.jpa.models.SaleRequest;
import com.example.jpa.models.SaleResponse;
import com.example.jpa.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class RestController {
    @Autowired
    private SaleService saleService;

    @GetMapping(value = "/welcome")
    public String one() {
        return "Welcome";
    }

    @GetMapping(value = "/numberofsales")
    public Integer getNumberOfSales() {
        return saleService.findAll().size();
    }

    @GetMapping(value = "/salesbysum", produces = APPLICATION_JSON_VALUE)
    public List<SaleResponse> getSalesBySum(@RequestBody String sum) {
        return saleService.getSalesBySum(Integer.valueOf(sum)); // 100
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<SaleResponse> findAll() {
        return saleService.findAll();
    }

    @GetMapping(value = "/{saleId}", produces = APPLICATION_JSON_VALUE)
    public SaleResponse findById(@PathVariable Integer saleId) {
        return saleService.findById(saleId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SaleResponse create(@RequestBody SaleRequest request) {
        return saleService.createSale(request);
    }

    @PatchMapping(value = "/{saleId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SaleResponse update(@PathVariable Integer saleId, @RequestBody SaleRequest request) {
        return saleService.update(saleId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{saleId}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer saleId) {
        saleService.delete(saleId);
    }
}
