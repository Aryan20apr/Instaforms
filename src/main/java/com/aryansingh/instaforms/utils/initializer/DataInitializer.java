package com.aryansingh.instaforms.utils.initializer;

import com.aryansingh.instaforms.models.entities.userAndAuth.Role;
import com.aryansingh.instaforms.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DataInitializer {

    public static final String SINGLE_USER = "ROLE_SINGLE_USER";
    public static final String ORG_USER = "ROLE_ORG_USER";
    public static final String ORG = "ROLE_ORG";

    private final RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void initRoles() {
        // Define your roles

        Role singleUserRole = new Role();
        singleUserRole.setRoleName(SINGLE_USER);

        Role orgUser = new Role();
        orgUser.setRoleName(ORG_USER);

        Role org = new Role();
        org.setRoleName(ORG);
        List<Role> rolesToInsert = List.of(singleUserRole, orgUser, org);


        // Fetch existing roles in a single query
        List<String> roleNames = rolesToInsert.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        List<Role> existingRoles = roleRepository.findByRoleNameIn(roleNames);
        Set<String> existingRoleNames = existingRoles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        // Filter out roles that already exist
        List<Role> rolesToSave = rolesToInsert.stream()
                .filter(role -> !existingRoleNames.contains(role.getRoleName()))
                .collect(Collectors.toList());

        // Save the new roles in a batch if there are any to save
        if (!rolesToSave.isEmpty()) {
            roleRepository.saveAll(rolesToSave);
        }
    }
}
