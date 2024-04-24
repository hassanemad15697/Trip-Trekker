package com.mentor.triptrekker.auth.repository;


import java.util.Optional;

import com.mentor.triptrekker.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}