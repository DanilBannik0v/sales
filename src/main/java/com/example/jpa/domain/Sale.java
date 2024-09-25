package com.example.jpa.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column("sum")
    private int sum;
    @Column("receiptDate")
    private LocalDateTime receiptDate;
    @Column("saleDate")
    private LocalDateTime saleDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id && sum == sale.sum && Objects.equals(receiptDate, sale.receiptDate) && Objects.equals(saleDate, sale.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sum, receiptDate, saleDate);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", sum=" + sum +
                ", receiptDate=" + receiptDate +
                ", saleDate=" + saleDate +
                '}';
    }
}
