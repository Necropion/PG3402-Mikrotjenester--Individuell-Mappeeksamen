package com.vehicle.authentication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
}
