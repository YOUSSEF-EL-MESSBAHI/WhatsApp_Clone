package com.example.ChatAppWebSock.service;

import com.example.ChatAppWebSock.exception.UserException;
import com.example.ChatAppWebSock.model.User;
import com.example.ChatAppWebSock.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User findUserById(Integer id) throws UserException;
    public User findUserByProfile(String jwt) throws UserException;
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;
    public List<User> searchUser(String query);
}
