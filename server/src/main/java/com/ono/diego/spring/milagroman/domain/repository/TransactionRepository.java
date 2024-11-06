package com.ono.diego.spring.milagroman.domain.repository;

import com.ono.diego.spring.milagroman.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(UUID id);
}
