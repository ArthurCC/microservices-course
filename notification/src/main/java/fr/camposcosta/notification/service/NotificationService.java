package fr.camposcosta.notification.service;

import fr.camposcosta.notification.entity.Notification;
import fr.camposcosta.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(Integer customerId) {

        notificationRepository.save(
                new Notification(
                        customerId,
                        LocalDateTime.now()
                )
        );
    }
}
