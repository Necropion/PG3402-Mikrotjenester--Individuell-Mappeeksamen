package com.vehicle.authentication.Service;

import com.vehicle.authentication.Model.UserModel;
import com.vehicle.authentication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserModel> allUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void addUser(UserModel userModel) {
        userRepository.save(userModel);
    }
}
