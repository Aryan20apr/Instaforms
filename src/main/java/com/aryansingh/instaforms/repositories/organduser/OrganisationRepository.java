package com.aryansingh.instaforms.repositories.organduser;

import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Optional<Organisation> findByEmail(String email);


    Optional<Organisation> findByOrganisationToken(String token);
}
