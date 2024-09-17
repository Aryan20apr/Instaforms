package com.aryansingh.instaforms.repositories;

import com.aryansingh.instaforms.models.entities.userAndAuth.OrganisationUser;
import com.aryansingh.instaforms.models.entities.userAndAuth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrEmail(String email);
}
