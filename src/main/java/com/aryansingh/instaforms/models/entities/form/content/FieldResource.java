package com.aryansingh.instaforms.models.entities.form.content;

import com.aryansingh.instaforms.models.entities.form.enums.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
public class FieldResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long id;

    private String url;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "field_id")
    private FormField formField;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FieldResource that))
            return false;
        return Objects.equals(url, that.url) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title);
    }
}
