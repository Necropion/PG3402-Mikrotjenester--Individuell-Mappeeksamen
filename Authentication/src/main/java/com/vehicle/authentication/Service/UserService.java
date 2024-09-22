package com.vehicle.authentication.Service;

import com.vehicle.authentication.Model.Authentication;
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

    public Authentication getUserById(String username, String password) {
        Authentication auth = new Authentication();
        if(userRepository.findByUsernameAndPassword(username, password) != null) {
            auth.setAuthentication(true);
            return auth;
        }
        return auth;
    }

    public void addUser(UserModel userModel) {
        userRepository.save(userModel);
    }
}
