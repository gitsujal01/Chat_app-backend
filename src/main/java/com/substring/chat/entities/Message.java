package com.substring.chat.entities;

import java.time.Instant;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {

    private String sender;
    private String content;
    private Instant timestamp;   // ✅ Instant only

    public Message() {}

    public Message(String sender, String content, Instant timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimestamp() {   
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {  // ✅ Instant only
        this.timestamp = timestamp;
    }
}