package com.ono.diego.spring.milagroman.web.dto;

import com.ono.diego.spring.milagroman.domain.entity.Budget;
import com.ono.diego.spring.milagroman.domain.entity.FinanceUser;
import com.ono.diego.spring.milagroman.domain.entity.Goal;
import com.ono.diego.spring.milagroman.domain.entity.Transaction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FinanceUserDTO {
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "E-mail is required")
    @Email(message = "Invalid e-mail")
    private String email;

    @NotNull(message = "Register date is required")
    @PastOrPresent(message = "Register date cannot be in the future")
    private LocalDate registerDate;

    private Boolean status = true;
    private List<Budget> budgets = new ArrayList<>();
    private List<Goal> goals = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public static FinanceUser toEntity(FinanceUserDTO financeUserDTO) {
        return FinanceUser
                .builder()
                .id(financeUserDTO.getId())
                .name(financeUserDTO.getName())
                .email(financeUserDTO.getEmail())
                .registerDate(financeUserDTO.getRegisterDate())
                .transactions(new ArrayList<>())
                .goals(new ArrayList<>())
                .budgets(new ArrayList<>())
                .status(financeUserDTO.getStatus())
                .build();

    }

    public static FinanceUserDTO fromEntity(FinanceUser financeUser) {
        return FinanceUserDTO
                .builder()
                .id(financeUser.getId())
                .name(financeUser.getName())
                .email(financeUser.getEmail())
                .registerDate(financeUser.getRegisterDate())
                // dto of relations
                .build();
    }
}
