package com.aryansingh.instaforms.models.entities.form.content;

import com.aryansingh.instaforms.models.entities.form.Form;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SectionResource> resources = new ArrayList<>();

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY,cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<FormField> formFields = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id",referencedColumnName = "form_id")
    private Form form;

    private Integer sequenceNo;


    private String token;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void generateToken() {
        if (this.token == null) {
            this.token = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Section section))
            return false;
        return Objects.equals(name, section.name) && Objects.equals(sequenceNo, section.sequenceNo) && Objects.equals(
                token, section.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sequenceNo, token);
    }
}
