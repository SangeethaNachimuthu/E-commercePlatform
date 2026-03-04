package se.lexicon.ecommerceplatform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CustomerRequestDTO(

        @NotBlank(message = "First Name can't be blank")
        @Size(max = 100, message = "First Name must not exceed 100 characters")
        String firstName,

        @NotBlank(message = "Last Name can't be blank")
        @Size(max = 100, message = "Last Name must not exceed 100 characters")
        String lastName,

        @NotBlank(message = "Email can't be blank")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password can't be blank")
        String password,

        @NotBlank(message = "Street can't be blank")
        String street,

        @NotBlank(message = "City can't be blank")
        String city,

        @NotBlank(message = "Zipcode can't be blank")
        String zipcode
) {
}
