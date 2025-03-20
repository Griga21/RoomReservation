package com.reservation.services;

import com.reservation.entity.Order;
import com.reservation.entity.Room;
import com.reservation.exceptions.DataTimeIllegalArgument;
import com.reservation.repositories.OrderRepository;
import com.reservation.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService {

    private RoomRepository roomRepository;
    private OrderRepository orderRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, OrderRepository orderRepository) {
        this.roomRepository = roomRepository;
        this.orderRepository = orderRepository;
    }

    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).get();
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Integer createRoom(Room room) {
        return roomRepository.save(room).getRoomId();
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllFilteredRoom(Integer countPlace) {
        return roomRepository.findAll().stream()
                .filter(room -> room.getCountSeats() >= countPlace).collect(Collectors.toList());
    }

    public List<Room> getAllFreeRooms(Timestamp startData, Timestamp endData) throws DataTimeIllegalArgument {
        if (checkData(startData, endData))
            throw new DataTimeIllegalArgument();

        List<Order> orderList = orderRepository.findAll().stream().filter(order -> order.getStartData().before(startData))
                .filter(order -> order.getStartData().after(endData)).filter(order -> order.getEndData().before(startData))
                .filter(order -> order.getEndData().after(endData)).collect(Collectors.toList());
        return orderList.stream().map(Order::getRoom).collect(Collectors.toList());
    }

    static public boolean checkData(Timestamp startData, Timestamp endData) {
        Integer startValue = startData.getDay() * 24 * 60 + startData.getHours() * 60 + startData.getMinutes();
        Integer endValue = endData.getDay() * 24 * 60 + endData.getHours() * 60 + endData.getMinutes();

        if (startValue - endValue % 29 != 0 || endData.before(startData))
            return false;
        else
            return true;
    }
}
