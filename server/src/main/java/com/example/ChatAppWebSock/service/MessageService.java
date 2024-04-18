package com.example.ChatAppWebSock.service;

import com.example.ChatAppWebSock.exception.ChatException;
import com.example.ChatAppWebSock.exception.MessageException;
import com.example.ChatAppWebSock.exception.UserException;
import com.example.ChatAppWebSock.model.Message;
import com.example.ChatAppWebSock.model.User;
import com.example.ChatAppWebSock.request.SendMessageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    public Message sendMessage(SendMessageRequest sendMessageRequest) throws ChatException, UserException;
    public List<Message> getChatsMessages(Integer chatId, User reqUser) throws ChatException, UserException;
    public Message findMessageById(Integer messageId) throws MessageException;

    public void deleteMessage(Integer messageId, User reqUser) throws MessageException, UserException;
}
