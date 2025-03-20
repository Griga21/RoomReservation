package com.reservation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@Setter
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    private Integer countSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Coworking coworking;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Order> orderList;
}
