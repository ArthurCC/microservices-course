package fr.camposcosta.customerapi.model;

public record NotificationRequest(
        Integer customerId,
        String customerEmail,
        String sender,
        String message
) {
}
