package fr.camposcosta.notification.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

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
    private String customerEmail;

    private String sender;

    private String message;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    public Notification(
            Integer customerId,
            String customerEmail,
            String sender,
            String message,
            LocalDateTime sentAt
    ) {
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.sender = sender;
        this.message = message;
        this.sentAt = sentAt;
    }
}
