package com.Vns.LMS.repository;

import com.Vns.LMS.entity.User;
import com.Vns.LMS.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    boolean existsByRollNo(String rollNo);
    Long countByRole(Role role);
}
