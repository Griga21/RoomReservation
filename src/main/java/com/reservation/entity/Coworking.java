package com.reservation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coworking")
@Setter
@Getter
public class Coworking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "coworking", fetch = FetchType.EAGER)
    private List<Room> roomList;
    @OneToMany(mappedBy = "coworking", fetch = FetchType.EAGER)
    private List<Order> orderList;
}
