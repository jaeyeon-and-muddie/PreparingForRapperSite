package com.skhu.practice.Repository;

import com.skhu.practice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String Email, String password);
    Optional<User> findByEmail(String Email);
}