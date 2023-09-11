package fr.camposcosta.notification.service;

import fr.camposcosta.notification.entity.Notification;
import fr.camposcosta.notification.model.NotificationRequest;
import fr.camposcosta.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(NotificationRequest notificationRequest) {

        notificationRepository.save(
                new Notification(
                        notificationRequest.customerId(),
                        notificationRequest.customerEmail(),
                        notificationRequest.sender(),
                        notificationRequest.message(),
                        LocalDateTime.now()
                )
        );
    }
}
