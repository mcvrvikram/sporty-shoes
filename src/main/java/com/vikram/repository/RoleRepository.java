package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.entity.CustomRole;
import com.vikram.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(CustomRole role);
}
