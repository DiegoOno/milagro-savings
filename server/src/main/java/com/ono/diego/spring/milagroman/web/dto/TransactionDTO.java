package com.ono.diego.spring.milagroman.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionDTO {
    private UUID id;
    private BigDecimal amount;
    private String description;
    private Long userId;
    private LocalDate date;
    private Boolean status;
}