package com.vehicle.authentication.Controller;

import com.vehicle.authentication.DTO.UserDTO;
import com.vehicle.authentication.Exception.User.InvalidDataException;
import com.vehicle.authentication.Exception.User.UserNotFoundException;
import com.vehicle.authentication.Model.Authentication;
import com.vehicle.authentication.Model.Availability;
import com.vehicle.authentication.Model.UserModel;
import com.vehicle.authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userList = userService.allUsers();
        log.info("User List Received {}", userList); // 200 OK

        return userList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("GET Request User by ID: {}", id);
        UserDTO user = userService.getOneUserById(id);

        if (user == null) {
            throw new UserNotFoundException("No user found with ID: " + id); // 404 Not Found
        }

        log.info("User retrieved: {}", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/auth")
    public Authentication authUser(@RequestParam String username, @RequestParam String password) {
        log.info("Authenticating Request User: {}", username);
        Authentication userAuth = userService.getUserByUsernameAndPassword(username, password);

        log.info("Authentication Result: {}", userAuth.isAuthentication());
        return userAuth;
    }

    @GetMapping("/availability/email")
    public Availability findUserByEmail(@RequestParam String email) {
        log.info("Checking Email availability: {}", email);

        return userService.getOneUserByEmail(email);
    }

    @GetMapping("/availability/username")
    public Availability findUserByUsername(@RequestParam String username) {
        log.info("Checking Username availability: {}", username);

        return userService.getOneUserByUsername(username);
    }

    @PostMapping()
    public UserModel registerUser(@RequestBody UserModel userModel) {
        log.info("POST Request User: {}", userModel);
        if(userModel.getUsername() == null || userModel.getPassword() == null || userModel.getEmail() == null) {
            throw new InvalidDataException("User Data has empty fields");
        }
        userService.addUser(userModel);

        log.info("User Added: {}", userModel);
        return userModel;
    }
}
