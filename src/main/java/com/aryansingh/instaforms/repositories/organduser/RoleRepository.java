package com.aryansingh.instaforms.repositories.organduser;

import com.aryansingh.instaforms.models.entities.userAndAuth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String name);

    List<Role> findByRoleNameIn(List<String> roleNames);

}
