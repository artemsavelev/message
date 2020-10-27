package com.example.clickdb.message.service;

import com.example.clickdb.message.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAll();

    Message getById(Long id);

    Message addOne(Message message);

    List<Message> addAll(List<Message> messages);
}
