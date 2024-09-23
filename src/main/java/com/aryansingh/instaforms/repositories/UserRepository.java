package com.aryansingh.instaforms.repositories;

import com.aryansingh.instaforms.models.entities.userAndAuth.SingleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SingleUser, Long> {

    Optional<SingleUser> findByUserNameOrEmail(String email,String username);

    Optional<SingleUser> findByUserToken(String token);
}
