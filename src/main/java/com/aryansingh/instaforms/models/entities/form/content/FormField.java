package com.aryansingh.instaforms.models.entities.form.content;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FormField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Integer id;

    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SectionResource> resources = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    FormFieldType type;

    Boolean required;

    String fieldToken;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    Section section;

    @Column(name = "seq_no")
    Integer sequenceNumber;

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
        if (this.fieldToken == null) {
            this.fieldToken = UUID.randomUUID().toString();
        }
    }

    // TODO: Style configuration






}
