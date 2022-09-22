package com.example.bookOrderService.util;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
public class CartModel {
    @Id
    @GenericGenerator(name = "cartdb",strategy = "increment")
    @GeneratedValue(generator = "cartdb")
    private Long cartId;
    private long userId;
    private long bookId;
    private int quantity;
    private double totalPrice;
}
