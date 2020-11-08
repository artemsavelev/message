package com.example.clickdb.message.dto;

import com.example.clickdb.message.model.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MessageDto {
    private Long id;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    public Message toMessage() {
        Message message = new Message();
        message.setId(id);
        message.setMessage(this.message);
        message.setCreatedDate(createdDate);
        return message;
    }

    public static MessageDto fromMessage(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessage(message.getMessage());
        messageDto.setCreatedDate(message.getCreatedDate());
        return messageDto;
    }

    public static List<MessageDto> messageDtoList(List<Message> messages) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        messages.forEach(message -> messageDtoList.add(fromMessage(message)));
        return messageDtoList;
    }
}
