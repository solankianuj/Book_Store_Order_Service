package com.example.bookOrderService.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="orderdatadb")
@Data
public class OrderModel {
    @Id
    @GenericGenerator(name = "orderdatadb",strategy = "increment")
    @GeneratedValue(generator = "orderdatadb")
    private Long orderId;
    private LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressModel address;
    private double price;
    private int quantity;
    private Long cartId;
    private Long userId;
    private Long bookId;
    private boolean isCancel;
}
