package com.aryansingh.instaforms.models.entities.form;

import com.aryansingh.instaforms.models.entities.userAndAuth.SingleUser;
import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
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

    @ManyToOne
    @JoinColumn(name = "form_id",referencedColumnName = "form_id",nullable = false)
    private Form form;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private SingleUser singleUser;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = true)
    private Organisation organisation;
}
