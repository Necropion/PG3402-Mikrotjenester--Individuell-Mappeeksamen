package com.vehicle.authentication.Controller;

import com.vehicle.authentication.Model.UserModel;
import com.vehicle.authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserModel> getAllUsers() {
        return userService.allUsers();
    }

    @PostMapping()
    public UserModel registerUser(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
        return userModel;
    }
}
