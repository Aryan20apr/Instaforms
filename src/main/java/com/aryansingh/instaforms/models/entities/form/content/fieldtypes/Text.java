package com.aryansingh.instaforms.models.entities.form.content.fieldtypes;

import com.aryansingh.instaforms.models.entities.form.content.FormField;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("TEXT")
public class Text extends FormField {

    @Column(name="text_max_length")
    private Integer maxLength;

    @Column(name="text_min_length")
    private Integer minLength;

    @Column(name="regex")
    private String pattern;


    @Override
    public String toString() {
        return "TextFormField{" +
                "maxLength=" + maxLength +
                ", minLength=" + minLength +
                ", pattern='" + pattern + '\'' +
                '}';
    }

}
