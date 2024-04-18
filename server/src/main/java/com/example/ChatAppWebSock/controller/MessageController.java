package com.example.ChatAppWebSock.controller;

import com.example.ChatAppWebSock.DTO.MessageDTO;
import com.example.ChatAppWebSock.controller.mapper.MessageDTOMapper;
import com.example.ChatAppWebSock.exception.ChatException;
import com.example.ChatAppWebSock.exception.MessageException;
import com.example.ChatAppWebSock.exception.UserException;
import com.example.ChatAppWebSock.model.Message;
import com.example.ChatAppWebSock.model.User;
import com.example.ChatAppWebSock.request.SendMessageRequest;
import com.example.ChatAppWebSock.response.ApiResponse;
import com.example.ChatAppWebSock.service.MessageService;
import com.example.ChatAppWebSock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody SendMessageRequest req, @RequestHeader("Authorization")String jwt) throws ChatException, UserException {
        User user = userService.findUserByProfile(jwt);
        req.setUserId(user.getId());
        Message message = messageService.sendMessage(req);
        MessageDTO messageDTO = MessageDTOMapper.toMessageDTO(message);

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageDTO>> getMessageHandler(@PathVariable Integer chatId, @RequestHeader("Authorization")String jwt) throws ChatException, UserException {
        User user = userService.findUserByProfile(jwt);
        List<Message> messages = messageService.getChatsMessages(chatId, user);
        List<MessageDTO> messageDTOs = MessageDTOMapper.toMessageDTOs(messages);

        return new ResponseEntity<>(messageDTOs, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Integer messageId, @RequestHeader("Authorization")String jwt) throws UserException, MessageException {
        User user = userService.findUserByProfile(jwt);
        messageService.deleteMessage(messageId, user);
        ApiResponse res = new ApiResponse("Message Deleted Successfully", false);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

}
