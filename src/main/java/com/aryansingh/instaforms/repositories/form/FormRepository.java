package com.aryansingh.instaforms.repositories.form;

import com.aryansingh.instaforms.models.entities.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Long> {

    Optional<Form> findByFormToken(String formToken);

}
