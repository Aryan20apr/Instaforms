package com.aryansingh.instaforms.models.entities.userAndAuth;


import com.aryansingh.instaforms.models.entities.form.FormOwner;
import com.fasterxml.jackson.annotation.JsonFilter;
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
@AllArgsConstructor
@Table( indexes = {
        @Index(name = "idx_user_name", columnList = "userName"),
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_user_token", columnList = "userToken")
})
@Builder
public class SingleUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    private String profileImage;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"))
    @Column(nullable = false)
    private Set<Role> roles = new HashSet<>();

    private String refreshToken;

    @Column(nullable = false)
    private String userToken;

    // List of responses

    // Set of forms
    @OneToMany(mappedBy = "singleUser", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<FormOwner> forms = new HashSet<>();

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
        if (this.userToken == null) {
            this.userToken = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SingleUser singleUser))
            return false;
        return Objects.equals(getUserName(), singleUser.getUserName()) && Objects.equals(getLastName(), singleUser.getLastName())
                && Objects.equals(getEmail(), singleUser.getEmail()) && Objects.equals(getPhoneNumber(),
                singleUser.getPhoneNumber()) && Objects.equals(getProfileImage(), singleUser.getProfileImage()) && Objects.equals(
                getUserToken(), singleUser.getUserToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getLastName(), getEmail(), getPhoneNumber(), getProfileImage(),
                getUserToken());
    }
}
