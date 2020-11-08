package com.example.clickdb.message.controller;

import com.example.clickdb.message.dao.MessageDao;
import com.example.clickdb.message.dto.MessageDto;
import com.example.clickdb.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    private final MessageDao messageDao;

    @Autowired
    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAll() {
        List<Message> result = messageDao.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public ResponseEntity<List<MessageDto>> saveAll(@RequestBody List<Message> messages) {
        if (messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        messageDao.addAll(messages);
        List<MessageDto> result = MessageDto.messageDtoList(messages);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
