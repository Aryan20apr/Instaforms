package com.aryansingh.instaforms.models.entities.form;


import com.aryansingh.instaforms.models.entities.form.content.Section;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private int id;

    private String title;

    @OneToOne
    @JoinColumn(name = "form_setting",referencedColumnName = "id")
    private FormSettings formSettings;

    private String description;

    private Boolean isEnabled;

    private String formToken;

    // Set of responses
    @OneToMany(mappedBy = "form",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    private List<FormResponse> responses = new ArrayList<>();

    // Set of sections
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Section> sections = new LinkedHashSet<>();

    private Boolean isUpdatable;

    // Save the token here
    private String createdBy;



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
        if (this.formToken == null) {
            this.formToken = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Form form))
            return false;
        return Objects.equals(getTitle(), form.getTitle()) && Objects.equals(getFormToken(), form.getFormToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getFormToken());
    }
}
