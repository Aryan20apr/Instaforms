package com.aryansingh.instaforms.models.entities.form.content;

import com.aryansingh.instaforms.models.entities.form.enums.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SectionResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long id;

    private String url;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    private Section section;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SectionResource that))
            return false;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getTitle());
    }
}
