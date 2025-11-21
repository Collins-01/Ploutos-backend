package com.collins.ploutos.ploutos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "account_type")
    private String accountType;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    //* Relationships
    @ManyToOne(
            optional = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name = "user_id")
    private UserModel user; // a  user can have many accounts
}
