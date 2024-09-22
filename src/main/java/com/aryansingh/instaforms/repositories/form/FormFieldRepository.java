package com.aryansingh.instaforms.repositories.form;

import com.aryansingh.instaforms.models.entities.form.content.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {

    Optional<FormField> findByFieldToken(String name);

}
