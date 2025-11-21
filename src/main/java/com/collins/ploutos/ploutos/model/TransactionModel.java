package com.collins.ploutos.ploutos.model;


import com.collins.ploutos.ploutos.enums.DirectionType;
import com.collins.ploutos.ploutos.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class TransactionModel {
    @Id
    @GeneratedValue
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private UUID transactionId;


    @Column(nullable = false, precision = 20, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DirectionType direction;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;            // expense, income, transfer, etc.

    @Column(nullable = true)
    private String description;


    @Column(nullable = true, name = "created_at")
    private Instant createdAt;

    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;


    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }


    //* Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountModel account;  // The relationship between account and transactions. All transactions belong to one account.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;  // The relationship between transactions and category.


}
