package com.aryansingh.instaforms.repositories;

import com.aryansingh.instaforms.models.entities.userAndAuth.SingleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SingleUser, Long> {

    Optional<SingleUser> findByUserNameOrEmail(String userName,String email);

    Optional<SingleUser> findByUserToken(String token);
}
