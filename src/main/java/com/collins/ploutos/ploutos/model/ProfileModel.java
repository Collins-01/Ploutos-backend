package com.collins.ploutos.ploutos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "display_name")
    private String displayName;


    @Column(nullable = false, name = "time_zone")
    private String timeZone;


    @Column(nullable = false, length = 3)
    private String locale;


    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    //* Relationship
    // Many-to-one relationship with User (inverse side of the OneToOne)
    @OneToOne(mappedBy = "profile")
    private UserModel user; // Important:  maps to the User's 'profile' field
}