package com.reservation.controllers;

import com.reservation.entity.Room;
import com.reservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getRoom")
    public Room getRoomById(@RequestParam("id") Integer id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/getAllRoom")
    public List<Room> getAllRoom() {
        return roomService.getAllRooms();
    }

    @GetMapping("/filteredByPlaceCount")
    public List<Room> getAllFilteredRoom(Integer countPlace) {
        return roomService.getAllFilteredRoom(countPlace);
    }

    @GetMapping("/getAllFreeRooms")
    public List<Room> getAllFreeRoom(@RequestParam("startData") Timestamp startData,
                                     @RequestParam("endData") Timestamp endData) {
        return roomService.getAllFreeRooms(startData, endData);
    }

    @PostMapping("/createRoom")
    public String createRoom(@RequestBody Room room) {
        Integer id = roomService.createRoom(room);
        return "Room with " + id + " has been created";
    }

    @PutMapping("/updateRoom")
    public String updateRoom(@RequestParam("id") Integer id, Room room) {
        Room roomDB = roomService.getRoomById(id);
        roomDB.setCountSeats(room.getCountSeats());
        return "Room with " + id + " has been update";
    }

    @DeleteMapping("/deleteRoom")
    public String deleteRoom(@RequestParam("id") Integer id) {
        roomService.deleteRoom(id);
        return "Room with " + id + " has been deleted";
    }
}
