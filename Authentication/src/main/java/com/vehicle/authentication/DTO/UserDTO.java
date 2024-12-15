package com.vehicle.authentication.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
}
