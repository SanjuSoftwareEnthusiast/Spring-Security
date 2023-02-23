package com.security.sanju.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.sanju.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String userName);
}
