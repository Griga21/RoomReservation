package com.reservation.pojo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class OrderRequest {
    private Long id;
    private Timestamp startData;
    private Timestamp endData;
    private Integer roomId;
    private Integer coworkingId;
}
