package fr.camposcosta.notification.controller;

import fr.camposcosta.notification.model.NotificationRequest;
import fr.camposcosta.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> createNotification(@RequestBody NotificationRequest notificationRequest) throws InterruptedException {

        log.info("create notification [{}]", notificationRequest);

        // Simulate latency
        Thread.sleep(5000);

        notificationService.createNotification(notificationRequest);

        return ResponseEntity.ok(null);
    }
}
