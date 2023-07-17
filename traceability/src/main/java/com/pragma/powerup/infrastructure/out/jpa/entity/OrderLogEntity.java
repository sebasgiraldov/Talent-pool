package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "orderLog")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLogEntity {
    @Id
    private String id;
    private Long clientId;
    private Long orderId;
    private Long restaurantId;
    private Long employeeId;
    private Date date;
    private String state;
}



