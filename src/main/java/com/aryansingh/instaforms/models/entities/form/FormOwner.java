package com.aryansingh.instaforms.models.entities.form;

import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
import com.aryansingh.instaforms.models.entities.userAndAuth.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FormOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "form_id",nullable = false)
    private Form form;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private Organisation organisation;
}
