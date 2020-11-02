package com.example.clickdb.message.controller;

import com.example.clickdb.message.model.Message;
import com.example.clickdb.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> list() {
        List<Message> messages = messageService.getAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "message/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> getOne(@PathVariable(name = "id") Long id) {
        Message message = messageService.getById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "message/add-one", method = RequestMethod.POST)
    public ResponseEntity<Message> save(@RequestBody Message message) {
        Message result = messageService.addOne(message);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    @RequestMapping(value = "message/add-all", method = RequestMethod.POST)
    public ResponseEntity<List<Message>> saveAll(@RequestBody List<Message> messages) {
        List<Message> result =  messageService.addAll(messages);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
