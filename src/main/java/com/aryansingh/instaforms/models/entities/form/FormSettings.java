package com.aryansingh.instaforms.models.entities.form;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FormSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String backgroundColor;

    private String backgroundImage;


    // TODO: Add video also

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FormSettings that))
            return false;
        return Objects.equals(getBackgroundColor(), that.getBackgroundColor()) && Objects.equals(getBackgroundImage(),
                that.getBackgroundImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBackgroundColor(), getBackgroundImage());
    }
}
