package com.aryansingh.instaforms.service.role;

import com.aryansingh.instaforms.models.entities.userAndAuth.Role;
import com.aryansingh.instaforms.repositories.organduser.RoleRepository;
import com.aryansingh.instaforms.utils.exceptions.ApiException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;
    @Override
    @Transactional
    public boolean createRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        roleRepository.save(role);
        return true;
    }

    @Override
    public Role findByRoleName(String roleName) {

        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if(role.isPresent()) {
            return role.get();
        } else{

            throw new ApiException("Role with this name does not exist");
        }
    }
}
