package com.example.clickdb.message.service;

import com.example.clickdb.message.model.Message;
import com.example.clickdb.message.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> getAll() {
        return messageRepo.findAll();
    }

    @Override
    public Message getById(Long id) {
        return messageRepo.findById(id).orElse(null);
    }

    @Override
    public Message addOne(Message message) {
        message.setCreatedDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @Override
    public List<Message> addAll(List<Message> messages) {
        return messageRepo.saveAll(messages);
    }
}
