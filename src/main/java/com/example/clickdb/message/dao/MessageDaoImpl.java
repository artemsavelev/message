package com.example.clickdb.message.dao;

import com.example.clickdb.message.conf.ClickHouseConfig;
import com.example.clickdb.message.dto.MessageDto;
import com.example.clickdb.message.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class MessageDaoImpl implements MessageDao {

    private final ClickHouseConfig clickHouseConfig;

    @Autowired
    public MessageDaoImpl(ClickHouseConfig clickHouseConfig) {
        this.clickHouseConfig = clickHouseConfig;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = clickHouseConfig.get();
        log.info("IN method getAll - {} messages found", messages.size());
        return messages;
    }

    @Override
    public void addAll(List<Message> messages) {
        if (messages.isEmpty()) {
            log.warn("IN method addAll - no list messages {}", (Object) null);
            return;
        }
        clickHouseConfig.send(messages);
        log.info("IN method addAll - messages: {} successfully added list messages", messages);
    }
}
