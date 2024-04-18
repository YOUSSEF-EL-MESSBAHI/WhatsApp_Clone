package com.example.ChatAppWebSock.controller;

import com.example.ChatAppWebSock.exception.ChatException;
import com.example.ChatAppWebSock.exception.UserException;
import com.example.ChatAppWebSock.model.Chat;
import com.example.ChatAppWebSock.model.Message;
import com.example.ChatAppWebSock.model.User;
import com.example.ChatAppWebSock.request.SendMessageRequest;
import com.example.ChatAppWebSock.service.ChatService;
import com.example.ChatAppWebSock.service.MessageService;
import com.example.ChatAppWebSock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
@RestController
public class RealtimeChat {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/group/public")
    public Message receiveMessage(@Payload Message message){

        System.out.println("receive message in public ---------- ");

//    	simpMessagingTemplate.convertAndSend("/group/" +req.getChatId().toString(), req);

        simpMessagingTemplate.convertAndSend("/group/"+message.getChat().getId().toString(), message);

        return message;
    }

    @MessageMapping("/chat/{groupId}")
    public void sendToUser(@Payload SendMessageRequest req, @Header("Authorization") String jwt, @DestinationVariable String groupId) throws UserException, ChatException {
        new Thread(() -> {
            try {
                // Traitement du message
                User user = userService.findUserByProfile(jwt);
                req.setUserId(user.getId());
                Chat chat = chatService.findChatById(req.getChatId());
                Message createdMessage = messageService.sendMessage(req);
                User receiverUser = reciver(chat, user);

                // Envoi du message
                simpMessagingTemplate.convertAndSendToUser(groupId, "/private", createdMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    public User reciver(Chat chat,User reqUser) {
        Iterator<User> iterator = chat.getUsers().iterator();

        User user1 = iterator.next(); // get the first user
        User user2 = iterator.next();

        if(user1.getId().equals(reqUser.getId())){
            return user2;
        }
        return user1;
    }
}
