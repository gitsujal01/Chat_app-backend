package com.substring.chat.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.substring.chat.config.AppConstants;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.services.RoomService;

@RestController
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService rs;

    // ✅ Create room
    @PostMapping("/create/{roomId}")
    public ResponseEntity<?> createRoom(@PathVariable String roomId) {
        return rs.createRoom(roomId);
    }

    // ✅ Join room
    @GetMapping("/join/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Room room = rs.findByRoomId(roomId);

        if (room == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Room not found");
        }

        return ResponseEntity.ok(room);
    }

    // ✅ Get messages
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Room room = rs.findByRoomId(roomId);

        if (room == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Message> messages = room.getMessages();

        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);

        return ResponseEntity.ok(messages.subList(start, end));
    }
}