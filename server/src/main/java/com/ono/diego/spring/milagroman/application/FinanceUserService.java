package com.ono.diego.spring.milagroman.application;

import com.ono.diego.spring.milagroman.domain.entity.FinanceUser;
import com.ono.diego.spring.milagroman.domain.repository.FinanceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceUserService {
    private final FinanceUserRepository financeUserRepository;

    @Autowired
    public FinanceUserService(FinanceUserRepository financeUserRepository) {
        this.financeUserRepository = financeUserRepository;
    }

    @Transactional(readOnly = true)
    public List<FinanceUser> findAll() {
        return financeUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<FinanceUser> findById(Long id) {
        return financeUserRepository.findById(id);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public FinanceUser create(FinanceUser financeUser) {
        return financeUserRepository.save(financeUser);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public FinanceUser update(FinanceUser financeUser) {
        FinanceUser recoveredUser = financeUserRepository.findById(financeUser.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id " + financeUser.getId()
                ));

        recoveredUser.mergeForUpdate(financeUser);

        return financeUserRepository.save(recoveredUser);
    }
}
