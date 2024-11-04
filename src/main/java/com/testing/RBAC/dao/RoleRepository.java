package com.testing.RBAC.dao;

import com.testing.RBAC.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
