package com.example.ChatAppWebSock.repository;

import com.example.ChatAppWebSock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.full_name) LIKE %:query%")
    List<User> searchUser(@Param("query") String query);

}
