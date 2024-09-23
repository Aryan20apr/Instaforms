package com.aryansingh.instaforms.service.role;

import com.aryansingh.instaforms.models.entities.userAndAuth.Role;

public interface RoleService {

 boolean createRole(String roleName);

 Role findByRoleName(String roleName);

}
