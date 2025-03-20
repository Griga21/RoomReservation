package com.reservation.services;

import com.reservation.entity.Coworking;
import com.reservation.entity.Order;
import com.reservation.entity.Room;
import com.reservation.exceptions.CoworkingNotFound;
import com.reservation.exceptions.RoomNotFound;
import com.reservation.pojo.OrderRequest;
import com.reservation.repositories.CoworkingRepository;
import com.reservation.repositories.OrderRepository;
import com.reservation.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static com.reservation.services.RoomService.checkData;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private RoomRepository roomRepository;
    private CoworkingRepository coworkingRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RoomRepository repository, CoworkingRepository coworkingRepository) {
        this.orderRepository = orderRepository;
        this.roomRepository = repository;
        this.coworkingRepository = coworkingRepository;
    }


    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Long createOrder(Order order) {
        return orderRepository.save(order).getOrderId();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Long bookingOrder(OrderRequest orderRequest) throws RoomNotFound, CoworkingNotFound {
        Room room = roomRepository.findById(orderRequest.getRoomId()).orElseThrow(RoomNotFound::new);
        Coworking coworking = coworkingRepository.findById(orderRequest.getCoworkingId()).orElseThrow(CoworkingNotFound::new);
        if (isTimeBusy(orderRequest.getStartData(), orderRequest.getEndData(), room.getOrderList())
                && checkData(orderRequest.getStartData(), orderRequest.getEndData()))
            throw new IllegalArgumentException("This time is busy");
        Order order = new Order();
        order.setStartData(orderRequest.getStartData());
        order.setEndData(orderRequest.getEndData());
        order.setRoom(room);
        order.setCoworking(coworking);
        return orderRepository.save(order).getOrderId();
    }

    private boolean isTimeBusy(Timestamp startData, Timestamp endData, List<Order> orderList) {
        for (Order order : orderList) {
            Timestamp otherStartTime = order.getStartData();
            Timestamp otherEndTime = order.getEndData();
            if (startData.before(otherStartTime)) {
                if (endData.after(otherStartTime) && endData.before(otherEndTime))
                    return true;
                if (endData.after(otherEndTime))
                    return true;
            } else {
                if (otherEndTime.after(startData) && otherEndTime.before(endData))
                    return true;
                if (otherEndTime.after(endData))
                    return true;
            }
        }
        return false;
    }
}
