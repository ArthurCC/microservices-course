package fr.camposcosta.customerapi.model;

public record CustomerResponse(
        Integer id,
        String firstName,
        String lastName,
        String email) {
}
