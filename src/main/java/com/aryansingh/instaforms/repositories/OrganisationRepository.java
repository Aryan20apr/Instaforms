package com.aryansingh.instaforms.repositories;

import com.aryansingh.instaforms.models.dtos.org.OrganisationDTO;
import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
import com.aryansingh.instaforms.models.entities.userAndAuth.OrganisationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Optional<Organisation> findByEmail(String email);


    Optional<Organisation> findByOrganisationToken(String token);
}
