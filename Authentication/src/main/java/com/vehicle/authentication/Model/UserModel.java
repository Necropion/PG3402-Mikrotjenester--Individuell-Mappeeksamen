package com.vehicle.authentication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
}
