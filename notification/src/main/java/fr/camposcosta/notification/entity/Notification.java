package fr.camposcosta.notification.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

    public Notification(
            Integer customerId,
            LocalDateTime timestamp
    ) {
        this.customerId = customerId;
        this.timestamp = timestamp;
    }

    @Id
    @SequenceGenerator(
            name = "notification_id_generator",
            sequenceName = "notification_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_generator"
    )
    private Integer id;

    @Column(nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
