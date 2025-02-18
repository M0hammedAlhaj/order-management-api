package com.spring.restapi.dao;

import com.spring.restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthorize(String authority);
}
