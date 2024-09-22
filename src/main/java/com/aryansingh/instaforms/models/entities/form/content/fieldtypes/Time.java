package com.aryansingh.instaforms.models.entities.form.content.fieldtypes;

import com.aryansingh.instaforms.models.entities.form.content.FormField;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("TIME")
public class Time extends FormField {

    private LocalTime time;
}
