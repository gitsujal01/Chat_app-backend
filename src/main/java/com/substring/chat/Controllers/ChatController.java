package com.substring.chat.Controllers;

import java.time.Instant;   // ✅ ye import add karo
// import java.time.LocalDateTime; ❌ isko hata do
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.payload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;

@RestController
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private RoomRepository rr;

    public ChatController(RoomRepository roomRepository) {
        this.rr = roomRepository;
    }

    // for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable("roomId") String roomId,
            @Payload MessageRequest request) {

        System.out.println("Received: " + request.getSender() + " -> " + request.getContent());

        Room roomm = rr.findByRoomId(roomId);
        if (roomm == null) {
            throw new RuntimeException("Room not found..");
        }

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimestamp(Instant.now());

        roomm.getMessages().add(message);
        rr.save(roomm);

        return message;
    }
}