package com.example.clickdb.message.service;

import com.example.clickdb.message.model.Message;
import com.example.clickdb.message.repo.MessageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = messageRepo.findAll();
        log.info("IN method getAll - {} messages found", messages.size());
        return messages;
    }

    @Override
    public Message getById(Long id) {
        Message message = messageRepo.findById(id).orElse(null);
        if (message == null) {
            log.warn("IN method findById - no message found by id: {}", id);
            return null;
        }
        log.info("IN method findById - message found by id: {}", message);
        return message;
    }

    @Override
    public Message addOne(Message message) {
        message.setCreatedDate(LocalDateTime.now());
        Message addedMessage = messageRepo.save(message);
        log.info("IN method addOne - message: {} successfully added", addedMessage);
        return addedMessage;
    }

    @Override
    public List<Message> addAll(List<Message> messages) {
        messages.forEach(message -> message.setCreatedDate(LocalDateTime.now()));
        List<Message> addedMessages = messageRepo.saveAll(messages);
        log.info("IN method addAll - messages: {} successfully added list messages", addedMessages);
        return addedMessages;
    }
}
