package com.microservices.user.repository;

import com.microservices.user.enums.RoleEnum;
import com.microservices.user.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleName(RoleEnum name);

}
