package fr.camposcosta.fraudapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FraudCheckHistory {

    public FraudCheckHistory(Integer customerId, Boolean isFraudster, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.isFraudster = isFraudster;
        this.createdAt = createdAt;
    }

    @Id
    @SequenceGenerator(
            name = "fraud_id_generator",
            sequenceName = "fraud_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_generator"
    )
    private Integer id;

    @Column(nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private Boolean isFraudster;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
