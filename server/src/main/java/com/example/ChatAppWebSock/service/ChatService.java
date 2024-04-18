package com.example.ChatAppWebSock.service;

import com.example.ChatAppWebSock.exception.ChatException;
import com.example.ChatAppWebSock.exception.UserException;
import com.example.ChatAppWebSock.model.Chat;
import com.example.ChatAppWebSock.request.GroupChatRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ChatService {
    public Chat createChat(Integer reqUserId, Integer userId) throws UserException;

    public Chat findChatById(Integer userId) throws ChatException;

    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;

    public Chat addUserToGroup(Integer userId, Integer chatId) throws UserException, ChatException;
    public Chat createGroup(GroupChatRequest req, Integer adminId) throws UserException;
    public Chat renameGroup(Integer chatId, String groupName, Integer reqUserId) throws UserException, ChatException;
    public Chat deleteChat(Integer chatId, Integer userId) throws ChatException, UserException;
    public Chat removeFromGroup(Integer chatId, Integer userId, Integer reqUserId) throws UserException, ChatException;
}
