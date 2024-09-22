package com.aryansingh.instaforms.models.entities.form.content.fieldtypes;

import com.aryansingh.instaforms.models.entities.form.content.FormField;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("Date")
public class Date extends FormField {


    private LocalTime time;
}
