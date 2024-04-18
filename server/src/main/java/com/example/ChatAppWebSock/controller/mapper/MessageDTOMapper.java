package com.example.ChatAppWebSock.controller.mapper;

import com.example.ChatAppWebSock.DTO.ChatDTO;
import com.example.ChatAppWebSock.DTO.MessageDTO;
import com.example.ChatAppWebSock.DTO.UserDTO;
import com.example.ChatAppWebSock.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageDTOMapper {
    public static MessageDTO toMessageDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();
        UserDTO userDTO = UserDTOMapper.toUserDTO(message.getUser());
        ChatDTO chatDTO = ChatDTOMapper.toChatDTO(message.getChat());

        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setTimeStamp(message.getTimeStamp());
        messageDTO.setUser(userDTO);
        messageDTO.setChat(chatDTO);

        return messageDTO;
    }

    public static List<MessageDTO> toMessageDTOs(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for(Message message: messages) {
            MessageDTO messageDTO = toMessageDTO(message);
            messageDTOs.add(messageDTO);
        }

        return messageDTOs;
    }

}
