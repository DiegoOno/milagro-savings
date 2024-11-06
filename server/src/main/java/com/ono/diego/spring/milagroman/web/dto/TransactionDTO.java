package com.ono.diego.spring.milagroman.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTO {
    private BigDecimal amount;
    private String description;
    private Long userId;
    private LocalDate date;
    private Boolean status;
    // Getters and setters


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}