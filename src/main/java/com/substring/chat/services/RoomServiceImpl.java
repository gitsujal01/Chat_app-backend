package com.substring.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.substring.chat.entities.Room;
import com.substring.chat.repositories.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository rr;

    @Override
    public ResponseEntity<?> createRoom(String roomId) {

        Room existingRoom = rr.findByRoomId(roomId);

        if (existingRoom != null) {
            return ResponseEntity.badRequest().body("Room already exists");
        }

        Room room = new Room();
        room.setRoomId(roomId);

        Room savedRoom = rr.save(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    @Override
    public Room findByRoomId(String roomId) {
        return rr.findByRoomId(roomId);
    }
}