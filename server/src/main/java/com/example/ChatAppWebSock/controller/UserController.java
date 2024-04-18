package com.example.ChatAppWebSock.controller;

import com.example.ChatAppWebSock.DTO.UserDTO;
import com.example.ChatAppWebSock.controller.mapper.UserDTOMapper;
import com.example.ChatAppWebSock.exception.UserException;
import com.example.ChatAppWebSock.model.User;
import com.example.ChatAppWebSock.request.UpdateUserRequest;
import com.example.ChatAppWebSock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user= userService.findUserByProfile(token);
        UserDTO userDTO = UserDTOMapper.toUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<HashSet<UserDTO>> searchUserHandler(@RequestParam("query") String query){
//        HashSet<User> users = new HashSet<>(userService.searchUser(query));
//        HashSet<UserDTO> userDTOs= UserDTOMapper.toUserDTOs(users);
//        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        System.out.println("Searching for users...");
        List<User> users = userService.searchUser(query);
        HashSet<User> set = new HashSet<>(users);
        HashSet<UserDTO> userDtos = UserDTOMapper.toUserDTOs(set);
        System.out.println("Search results: " + userDtos);
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUserHandler(
            @PathVariable Integer userId,
            @RequestBody UpdateUserRequest req
    ) throws UserException {

        User updatedUser=userService.updateUser(userId, req);
        UserDTO userDTO = UserDTOMapper.toUserDTO(updatedUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}









