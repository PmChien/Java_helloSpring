package com.example.helloSpring.service;

import com.example.helloSpring.DTO.UserRequest;
import com.example.helloSpring.DTO.UserResponse;
import com.example.helloSpring.entity.User;
import com.example.helloSpring.repository.UserReponsitory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {
    @Autowired
    private UserReponsitory userReponsitory;

    public User createUser(UserRequest request) {
        // Kiểm tra xem userName đã tồn tại chưa
        if (userReponsitory.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhoneNumber());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUserName());
        return userReponsitory.save(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userReponsitory.findAll();

        List<UserResponse> userResponses = new ArrayList<UserResponse>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getUserName()
            );


            userResponses.add(userResponse); // Thêm vào danh sách userResponses
        }
        return userResponses;
    }

    public UserResponse getUserById(Long id) {
        return userReponsitory.findById(id)
                .map(user -> new UserResponse(user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getPhone()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User updateUser(Long id, UserRequest request) {
        User userExisted = userReponsitory.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // update user detail
        userExisted.setFirstName(request.getFirstName());
        userExisted.setLastName(request.getLastName());
        userExisted.setEmail(request.getEmail());
        userExisted.setPhone(request.getPhoneNumber());
        userExisted.setUserName(request.getUserName());
        return userReponsitory.save(userExisted);
    }
    public void deleteUser(Long id) {
        if (!userReponsitory.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userReponsitory.deleteById(id);
    }
}
