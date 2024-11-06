package com.ono.diego.spring.milagroman.web;

import com.ono.diego.spring.milagroman.application.TransactionService;
import com.ono.diego.spring.milagroman.domain.entity.Transaction;
import com.ono.diego.spring.milagroman.domain.entity.User;
import com.ono.diego.spring.milagroman.web.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction savedTransaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAllTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable("id") UUID id) {
        Optional<Transaction> transaction = transactionService.findTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") UUID id, @RequestBody TransactionDTO transactionDTO) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Boolean> activeTransaction(@PathVariable("id") UUID id) {
        transactionService.updateTransactionStatus(id, true);
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/{id}/inactive")
    public ResponseEntity<Boolean> inactiveTransaction(@PathVariable("id") UUID id) {
        transactionService.updateTransactionStatus(id, false);
        return ResponseEntity.ok(false);
    }
}
