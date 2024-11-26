package com.ono.diego.spring.milagroman.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    private UUID id;

    @Getter
    @Setter
    @Column(name = "amount")
    private BigDecimal amount;

    @Getter
    @Setter
    @Column(length = 500)
    private String description;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "finance_user_id", nullable = false)
    private FinanceUser financeUser;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @Getter
    @Setter
    @Column(name = "status")
    private Boolean status;

    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;
}
