package fr.camposcosta.notification.controller;

import fr.camposcosta.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Void> createNotification(@PathVariable Integer customerId) {

        log.info("create notification [customerId={}]", customerId);

        notificationService.createNotification(customerId);

        return ResponseEntity.ok(null);
    }
}
