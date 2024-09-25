package com.example.jpa.services;

import com.example.jpa.domain.Sale;
import com.example.jpa.models.SaleRequest;
import com.example.jpa.models.SaleResponse;

import com.example.jpa.repositories.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(this::buildSaleResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> getSalesBySum(Integer sum) {
        return saleRepository.findAll()
                .stream()
                .map(this::buildSaleResponse)
                .filter(saleResponse -> saleResponse.getSum() > sum)
                .collect(Collectors.toList());
    }

    @NotNull
    private SaleResponse buildSaleResponse(@NotNull Sale sale) {
        return new SaleResponse()
                .setId(sale.getId())
                .setSum(sale.getSum())
                .setReceiptDate(sale.getReceiptDate())
                .setSaleDate(sale.getSaleDate());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public SaleResponse findById(@NotNull Integer saleId) {
        return saleRepository.findById(saleId)
                .map(this::buildSaleResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + saleId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public SaleResponse createSale(@NotNull SaleRequest request) {
        Sale sale = buildSaleRequest(request);
        return buildSaleResponse(saleRepository.save(sale));
    }

    @NotNull
    private Sale buildSaleRequest(@NotNull SaleRequest request) {
        return new Sale()
                .setId(request.getId())
                .setSum(request.getSum())
                .setReceiptDate(request.getReceiptDate())
                .setSaleDate(request.getSaleDate());
    }

    @NotNull
    @Override
    @Transactional
    public SaleResponse update(@NotNull Integer saleId, @NotNull SaleRequest request) {
        Sale sale =  saleRepository.findById(saleId)
                .orElseThrow(() -> new EntityNotFoundException("User " + saleId + " is not found"));
        saleUpdate(sale, request);
        return buildSaleResponse(saleRepository.save(sale));
    }

    private void saleUpdate(@NotNull Sale sale, @NotNull SaleRequest request) {
        ofNullable(request.getId()).map(sale::setId);
        ofNullable(request.getSum()).map(sale::setSum);
        ofNullable(request.getReceiptDate()).map(sale::setReceiptDate);
        ofNullable(request.getSaleDate()).map(sale::setSaleDate);
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer saleId) {
        saleRepository.deleteById(saleId);
    }
}
