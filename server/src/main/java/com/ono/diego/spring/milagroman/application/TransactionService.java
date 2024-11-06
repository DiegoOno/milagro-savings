package com.ono.diego.spring.milagroman.application;

import com.ono.diego.spring.milagroman.domain.entity.Transaction;
import com.ono.diego.spring.milagroman.domain.entity.User;
import com.ono.diego.spring.milagroman.domain.repository.TransactionRepository;
import com.ono.diego.spring.milagroman.domain.repository.UserRepository;
import com.ono.diego.spring.milagroman.web.dto.TransactionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(TransactionDTO transactionDTO) {
        System.out.println(transactionDTO);
        User user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setUser(user);
        transaction.setDate(transactionDTO.getDate());
        transaction.setStatus(transactionDTO.getStatus());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }

    public Transaction updateTransaction(UUID id, TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        User user = userRepository.findById(transactionDTO.getUserId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found"));

        if (!Objects.equals(existingTransaction.getUser().getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not match");
        }
        existingTransaction.setId(id);
        existingTransaction.setAmount(transactionDTO.getAmount());
        existingTransaction.setDescription(transactionDTO.getDescription());

        return transactionRepository.save(existingTransaction);
    }

    public void updateTransactionStatus(UUID id, Boolean status) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        existingTransaction.setStatus(status);
        transactionRepository.save(existingTransaction);
    }
}
