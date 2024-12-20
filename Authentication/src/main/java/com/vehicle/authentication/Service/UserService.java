package com.vehicle.authentication.Service;

import com.vehicle.authentication.DTO.UserDTO;
import com.vehicle.authentication.Model.Authentication;
import com.vehicle.authentication.Model.Availability;
import com.vehicle.authentication.Model.UserModel;
import com.vehicle.authentication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Fetch
    public List<UserDTO> allUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(
                user.getId(), user.getUsername(), user.getEmail())).collect(Collectors.toList()
        );
    }

    public UserDTO getOneUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()) {
            return new UserDTO(user.get().getId(), user.get().getUsername(), user.get().getEmail());
        }
        return null;
    }

    // Availability
    public Availability getOneUserByEmail(String email) {
        Availability availability = new Availability();
        UserModel user = userRepository.findByEmail(email);
        if(user != null) {
            return availability;
        }

        availability.setAvailability(true);
        return availability;
    }

    public Availability getOneUserByUsername(String username) {
        Availability availability = new Availability();
        UserModel user = userRepository.findByUsername(username);
        if(user != null) {
            return availability;
        }

        availability.setAvailability(true);
        return availability;
    }

    // Authentication
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

    // Registration
    public void addUser(UserModel userModel) {
        userRepository.save(userModel);
    }
}
