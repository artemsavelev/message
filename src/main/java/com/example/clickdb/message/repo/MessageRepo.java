package com.example.clickdb.message.repo;

import com.example.clickdb.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
