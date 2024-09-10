package com.example.helloSpring.controller;

import com.example.helloSpring.DTO.UserRequest;
import com.example.helloSpring.DTO.UserResponse;
import com.example.helloSpring.helper.ApiResponse;
import com.example.helloSpring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        // Tạo đối tượng ApiResponse
        ApiResponse response = new ApiResponse();
        response.setCode(HttpStatus.OK.value()); // Mã trạng thái HTTP
        response.setMessage("User added successfully"); // Thông điệp

        // Trả về ResponseEntity với ApiResponse và mã trạng thái
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/update/{userId}")
    public String updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequest userRequest) {
        userService.updateUser(userId, userRequest);
        return "update user success";
    }
    @PatchMapping("update/{userId}")
    public String patchUser(@PathVariable("userId") int userId, @RequestBody UserRequest userRequest) {
        return "update user success";
    }
    @DeleteMapping("/delete/{userId}")
    ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
        ApiResponse response = new ApiResponse();
        try {
            userService.deleteUser(userId);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("User deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            // Nếu gặp lỗi RuntimeException (như User not found)
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage()); // Thông điệp lỗi chi tiết
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            // Xử lý các lỗi khác
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse> getAllUser(){
        try {

        ApiResponse response = new ApiResponse();
        List<UserResponse> users = userService.getAllUsers();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("getAllUsers success");
        response.setData(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse());
        }
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }
}
