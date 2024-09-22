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

    public UserModel getOneUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public Authentication getUserByUsernameAndPassword(String username, String password) {
        Authentication auth = new Authentication();
        UserModel user = userRepository.findByUsernameAndPassword(username, password);
        if(user != null) {
            auth.setAuthentication(true);
            auth.setUserId(user.getId());
            return auth;
        }
        return auth;
    }

    public void addUser(UserModel userModel) {
        userRepository.save(userModel);
    }
}
