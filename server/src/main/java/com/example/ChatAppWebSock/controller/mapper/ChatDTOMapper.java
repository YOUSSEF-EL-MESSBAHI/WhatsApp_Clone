package com.example.ChatAppWebSock.controller.mapper;

import com.example.ChatAppWebSock.DTO.ChatDTO;
import com.example.ChatAppWebSock.DTO.MessageDTO;
import com.example.ChatAppWebSock.DTO.UserDTO;
import com.example.ChatAppWebSock.model.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatDTOMapper {
    public static ChatDTO toChatDTO(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();
        Set<UserDTO> userDTOs = UserDTOMapper.toUserDTOs(chat.getUsers());
        Set<UserDTO> admins = UserDTOMapper.toUserDTOs(chat.getAdmins());
        List<MessageDTO> messageDTOs = MessageDTOMapper.toMessageDTOs(chat.getMessages());

        chatDTO.setId(chat.getId());
        chatDTO.setChat_name(chat.getChat_name());
        chatDTO.setChat_image(chat.getChat_image());
        chatDTO.setCreated_by(chatDTO);
        chatDTO.setIs_group(chat.getIs_group());
        chatDTO.setAdmins(admins);
        chatDTO.setMessages(messageDTOs);
        chatDTO.setUsers(userDTOs);


        return chatDTO;
    }
    public static List<ChatDTO> toChatDTOs(List<Chat> chats) {
        List<ChatDTO> chatDTOs = new ArrayList<>();
        for(Chat chat: chats) {
            ChatDTO chatDTO = toChatDTO(chat);
            chatDTOs.add(chatDTO);
        }
        return chatDTOs;
    }
}
