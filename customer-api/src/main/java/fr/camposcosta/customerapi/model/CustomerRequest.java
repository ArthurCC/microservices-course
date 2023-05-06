package fr.camposcosta.customerapi.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "firstName cannot be blank")
        String firstName,
        @NotBlank(message = "lastName cannot be blank")
        String lastName,
        @NotBlank(message = "email cannot be blank")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email is invalid")
        String email) {
}
