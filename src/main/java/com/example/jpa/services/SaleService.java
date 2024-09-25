package com.example.jpa.services;

import com.example.jpa.models.SaleRequest;
import com.example.jpa.models.SaleResponse;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public interface SaleService {
    @NotNull
    List<SaleResponse> findAll();

    @NotNull
    List<SaleResponse> getSalesBySum(Integer sum);

    @NotNull
    SaleResponse findById(@NotNull Integer saleId);

    @NotNull
    SaleResponse createSale(@NotNull SaleRequest request);

    @NotNull
    SaleResponse update(@NotNull Integer saleId, @NotNull SaleRequest request);

    void delete(@NotNull Integer saleId);
}
