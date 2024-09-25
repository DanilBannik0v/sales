package com.example.jpa.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SaleResponse {
    private int id;
    private int sum;
    private LocalDateTime receiptDate;
    private LocalDateTime saleDate;
}
