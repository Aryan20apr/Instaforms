package com.aryansingh.instaforms.repositories.organduser;

import com.aryansingh.instaforms.models.entities.userAndAuth.OrganisationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgUserRepository extends JpaRepository<OrganisationUser,Long> {


    Optional<OrganisationUser> findByEmail(String email);
}
