package com.holdmelol.someshop.services;

import com.holdmelol.someshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
}
