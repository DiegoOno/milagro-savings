package com.ono.diego.spring.milagroman.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity(name = "Goal")

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal {

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

    @ManyToOne
    @JoinColumn(name = "finance_user_id")
    @Getter
    @Setter
    private FinanceUser financeUser;

    @OneToMany(mappedBy = "goal")
    private List<Transaction> transactions;
}
