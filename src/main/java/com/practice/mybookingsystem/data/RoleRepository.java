package com.practice.mybookingsystem.data;

import com.practice.mybookingsystem.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
