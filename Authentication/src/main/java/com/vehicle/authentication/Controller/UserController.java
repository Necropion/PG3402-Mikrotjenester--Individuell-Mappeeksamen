package com.vehicle.authentication.Controller;

import com.vehicle.authentication.Exception.NoUserFoundException;
import com.vehicle.authentication.Model.Authentication;
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

    // Console Coloring for improved logging visibility
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";  // Green for successful operations
    private static final String ANSI_YELLOW = "\u001B[33m"; // Yellow for warnings
    private static final String ANSI_RED = "\u001B[31m";    // Red for errors

    private final UserService userService;

    @GetMapping()
    public List<UserModel> getAllUsers() {
        List<UserModel> userList = userService.allUsers();
        log.info(ANSI_GREEN + "User List Retrieved: {}" + ANSI_RESET, userList); // 200 OK

        return userList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        log.info(ANSI_GREEN + "GET User by ID: {}" + ANSI_RESET, id);
        UserModel user = userService.getOneUserById(id);

        if (user == null) {
            log.warn(ANSI_YELLOW + "User with ID {} not found" + ANSI_RESET, id);
            throw new NoUserFoundException("No user found with ID: " + id); // 404 Not Found
        }

        log.info(ANSI_GREEN + "User retrieved: {}" + ANSI_RESET, user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/auth")
    public Authentication authUser(@RequestParam String username, @RequestParam String password) {
        log.info(ANSI_GREEN + "Authenticating User: {}" + ANSI_RESET, username);
        Authentication userAuth = userService.getUserByUsernameAndPassword(username, password);

        log.info(ANSI_GREEN + "Authentication Result: {}" + ANSI_RESET, userAuth.isAuthentication());
        return userAuth;
    }

    @PostMapping()
    public UserModel registerUser(@RequestBody UserModel userModel) {
        log.info(ANSI_GREEN + "POST User: {}" + ANSI_RESET, userModel);
        userService.addUser(userModel);

        log.info(ANSI_GREEN + "User Added: {}" + ANSI_RESET, userModel);
        return userModel;
    }
}
