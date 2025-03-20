package com.reservation.controllers;

import com.reservation.entity.Order;
import com.reservation.exceptions.CoworkingNotFound;
import com.reservation.exceptions.RoomNotFound;
import com.reservation.pojo.OrderRequest;
import com.reservation.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getOrder")
    public Order getOrderById(@RequestParam("id") Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Order order) {
        Long id = orderService.createOrder(order);
        return "Order with " + id + " has been created";
    }

    @PostMapping("/bookingOrder")
    public String bookingOrder(@RequestBody OrderRequest orderRequest){
        try {
            Long id = orderService.bookingOrder(orderRequest);
            return "Order with " + id + " has been saved";
        }catch (RoomNotFound e){
            return "Room with this id was not found";
        }catch (CoworkingNotFound e){
            return "Coworking with this id was not found";
        }
    }

    @PutMapping("/updateOrder")
    public String updateOrder(@RequestParam("id") Long id, Order order) {
        Order orderDB = orderService.getOrderById(id);
        orderDB.setStartData(order.getStartData());
        orderDB.setEndData(order.getEndData());
        orderDB.setRoom(order.getRoom());
        orderDB.setCoworking(order.getCoworking());
        return "order with " + id + " has been update";
    }

    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return "order with " + id + " has been deleted";
    }
}
