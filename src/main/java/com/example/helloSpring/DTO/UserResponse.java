package com.example.helloSpring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;

    private String phoneNumber;
    private String userName;
}
