package com.ono.diego.spring.milagroman.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "finance_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "register_date", updatable = false)
    @PastOrPresent
    private LocalDate registerDate;

    @Getter
    @Setter
    @Column(name = "status")
    private Boolean status = true;

    @OneToMany(mappedBy = "financeUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Budget> budgets;

    @OneToMany(mappedBy = "financeUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Goal> goals;

    @OneToMany(mappedBy = "financeUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactions;

    public void mergeForUpdate(FinanceUser financeUser) {
        this.name = financeUser.getName();
        this.email = financeUser.getEmail();
    }
}
