package com.ono.diego.spring.milagroman.web;

import com.ono.diego.spring.milagroman.application.FinanceUserService;
import com.ono.diego.spring.milagroman.domain.entity.FinanceUser;
import com.ono.diego.spring.milagroman.web.dto.FinanceUserDTO;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/finance-user")
public class FinanceUserController {
    private final FinanceUserService financeUserService;

    @Autowired
    public FinanceUserController(FinanceUserService financeUserService) {
        this.financeUserService = financeUserService;
    }

    @GetMapping
    public List<FinanceUserDTO> findAllFinanceUsers() {
        return financeUserService.findAll()
                .stream()
                .map(FinanceUserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceUserDTO> findByIdFinanceUser(@PathVariable("id") Long id) {
        return financeUserService.findById(id)
                .map((financeUser) -> ResponseEntity.ok(FinanceUserDTO.fromEntity(financeUser)))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id " + id
                ));

    }

    @PostMapping
    public ResponseEntity<?> createFinanceUser(@Valid @RequestBody FinanceUserDTO financeUserDTO) {
        try {
            FinanceUser createdFinanceUser = financeUserService.create(FinanceUserDTO.toEntity(financeUserDTO));
            return ResponseEntity.status(HttpStatus.OK).body(FinanceUserDTO.fromEntity(createdFinanceUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateFinanceuser(@Valid @RequestBody FinanceUserDTO financeUserDTO) {
        try {
            FinanceUser updatedFinanceUser = financeUserService.update(FinanceUserDTO.toEntity(financeUserDTO));
            return ResponseEntity.status(HttpStatus.OK).body(FinanceUserDTO.fromEntity(updatedFinanceUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
