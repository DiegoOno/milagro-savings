package com.ono.diego.spring.milagroman.domain.repository;

import com.ono.diego.spring.milagroman.domain.entity.FinanceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceUserRepository extends JpaRepository<FinanceUser, Long> {
}
