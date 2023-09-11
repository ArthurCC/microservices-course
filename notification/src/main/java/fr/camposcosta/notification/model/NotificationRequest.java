package fr.camposcosta.notification.model;

public record NotificationRequest(
        Integer customerId,
        String customerEmail,
        String sender,
        String message
) {
}
