package com.skhu.practice.repository;

import com.skhu.practice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmailAndPassword(String Email, String password);

    Optional<Users> findByUsername(String username);
}
