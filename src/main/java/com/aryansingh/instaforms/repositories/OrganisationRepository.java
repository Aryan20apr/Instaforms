package com.aryansingh.instaforms.repositories;

import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
import com.aryansingh.instaforms.models.entities.userAndAuth.OrganisationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Optional<Organisation> findByEmail(String email);
}
