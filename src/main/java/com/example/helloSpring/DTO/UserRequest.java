package com.example.helloSpring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "firstName must be not blank")
    private String firstName;
    @NotNull(message = "lastName must be not null")
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String userName;

}
