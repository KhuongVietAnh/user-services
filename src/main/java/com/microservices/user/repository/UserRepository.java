package com.microservices.user.repository;

import com.microservices.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Boolean existsByEmail(String email);
}
