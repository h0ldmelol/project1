package com.holdmelol.someshop.repositories;

import com.holdmelol.someshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);
}
