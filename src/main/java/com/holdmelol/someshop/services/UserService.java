package com.holdmelol.someshop.services;

import com.holdmelol.someshop.dto.UserDTO;
import com.holdmelol.someshop.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);

    List<UserDTO> getAll();

    User findByName(String name);

    void updateProfile(UserDTO dto);
}
