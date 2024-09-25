package com.aryansingh.instaforms.models.entities.userAndAuth;

import com.aryansingh.instaforms.models.entities.form.FormOwner;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String organizationName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;


    private String profileImage;

    @Column(nullable = false)
    private String about;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "org_role",joinColumns = @JoinColumn(name = "org_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"))
    @Column(nullable = false)
    private Set<Role> roles = new HashSet<>();
    // Set of forms
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<FormOwner> forms = new HashSet<>();

    @Column(nullable = false)
    private String organisationToken;

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
        if (this.organisationToken == null) {
            this.organisationToken = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Organisation that))
            return false;
        return Objects.equals(getOrganizationName(), that.getOrganizationName()) && Objects.equals(getEmail(),
                that.getEmail()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber()) && Objects.equals(
                getOrganisationToken(), that.getOrganisationToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganizationName(), getEmail(), getPhoneNumber(), getOrganisationToken());
    }
}
