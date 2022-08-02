package com.holdmelol.someshop.services;

import com.holdmelol.someshop.dto.UserDTO;
import com.holdmelol.someshop.entities.Role;
import com.holdmelol.someshop.entities.User;
import com.holdmelol.someshop.repositories.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

   public void setUserRepo(UserRepo userRepo) {
       this.userRepo = userRepo;
   }

   public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
       this.passwordEncoder = passwordEncoder;
   }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepo.save(user);
        return true;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDTO toDto(User user) {
       return UserDTO.builder()
               .username(user.getName())
               .email(user.getEmail())
               .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }
}
