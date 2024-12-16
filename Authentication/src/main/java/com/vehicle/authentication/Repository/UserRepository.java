package com.vehicle.authentication.Repository;

import com.vehicle.authentication.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsernameAndPassword(String username, String password);
    UserModel findByEmail(String email);
    UserModel findByUsername(String username);
}
