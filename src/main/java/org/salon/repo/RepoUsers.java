package org.salon.repo;


import org.salon.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface RepoUsers extends JpaRepository<Users, Long> {
    List<Users> findAllByOrderByRole();
    List<Users> findByRole(String role);
    List<Users> findByRoleIn(List<String> roles);
    List<Users> findByRoleNot(String role);
    Users findByUsername(String username);

    Users findByUsernameAndLastName(String username, String lastName);

    Users findByEmail(String email);
}
