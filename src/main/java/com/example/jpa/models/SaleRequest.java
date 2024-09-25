package com.example.jpa.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SaleRequest {
    private int id;
    private int sum;
    private LocalDateTime receiptDate;
    private LocalDateTime saleDate;

}
