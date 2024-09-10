package com.example.helloSpring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Nếu id tự động tăng
    private Long id; // Khóa chính của bảng

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firt_name") // Sửa thành first_name
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String userName;
}
