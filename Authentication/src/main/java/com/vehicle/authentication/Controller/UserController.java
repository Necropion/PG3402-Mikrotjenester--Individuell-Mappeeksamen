package com.vehicle.authentication.Controller;

import com.vehicle.authentication.Exception.NoUserFoundException;
import com.vehicle.authentication.Model.Authentication;
import com.vehicle.authentication.Model.UserModel;
import com.vehicle.authentication.Service.UserService;
import com.vehicle.authentication.Utility.ConsoleColor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserModel> getAllUsers() {
        List<UserModel> userList = userService.allUsers();
        log.info(ConsoleColor.Green("User List Received {}"), userList); // 200 OK

        return userList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        log.info(ConsoleColor.Green("GET User by ID: {}"), id);
        UserModel user = userService.getOneUserById(id);

        if (user == null) {
            log.warn(ConsoleColor.Yellow("User with ID {} not found"), id);
            throw new NoUserFoundException("No user found with ID: " + id); // 404 Not Found
        }

        log.info(ConsoleColor.Green("User retrieved: {}"), user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/auth")
    public Authentication authUser(@RequestParam String username, @RequestParam String password) {
        log.info(ConsoleColor.Green("Authenticating User: {}"), username);
        Authentication userAuth = userService.getUserByUsernameAndPassword(username, password);

        log.info(ConsoleColor.Green("Authentication Result: {}"), userAuth.isAuthentication());
        return userAuth;
    }

    @PostMapping()
    public UserModel registerUser(@RequestBody UserModel userModel) {
        log.info(ConsoleColor.Green("POST User: {}"), userModel);
        userService.addUser(userModel);

        log.info(ConsoleColor.Green("User Added: {}"), userModel);
        return userModel;
    }
}
