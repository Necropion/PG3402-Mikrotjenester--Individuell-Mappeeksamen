package com.vehicle.authentication.Controller;

import com.vehicle.authentication.Model.Authentication;
import com.vehicle.authentication.Model.UserModel;
import com.vehicle.authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserModel> getAllUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getOneUserById(id);
    }

    @GetMapping("/auth")
    public Authentication authUser(@RequestParam String username, @RequestParam String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    @PostMapping()
    public UserModel registerUser(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
        return userModel;
    }
}
