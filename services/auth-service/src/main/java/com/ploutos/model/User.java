package com.ploutos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private String status; // enum (suspended, active, deleted)

    @Column
    private String createdAt;

    @Column
    private String updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date().toString();
        updatedAt = new Date().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date().toString();
    }

}
