package com.example.clickdb.message.dao;

import com.example.clickdb.message.model.Message;

import java.util.List;

public interface MessageDao {

    List<Message> getAll();

    void addAll(List<Message> messages);
}
