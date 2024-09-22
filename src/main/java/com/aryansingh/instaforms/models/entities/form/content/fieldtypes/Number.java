package com.aryansingh.instaforms.models.entities.form.content.fieldtypes;

import com.aryansingh.instaforms.models.entities.form.content.FormField;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("NUMBER")
public class Number extends FormField {

    Double min;

    Double max;

    Boolean decimalAllowed;

    int maxDecimalDigits;

    Boolean percentage;
}
