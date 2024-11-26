package com.ono.diego.spring.milagroman.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "budget")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private BigDecimal amount;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "finance_user_id")
    private FinanceUser financeUser;
}
