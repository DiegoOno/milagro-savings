package com.ono.diego.spring.milagroman.domain.repository;

import com.ono.diego.spring.milagroman.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
