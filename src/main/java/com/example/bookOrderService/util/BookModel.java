package com.example.bookOrderService.util;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
public class BookModel {
    @Id
    @GenericGenerator(name = "bookData",strategy = "increment")
    @GeneratedValue(generator = "bookData")
    private Long bookId;
    private String bookName;
    private String author;
    private String description;
    private byte[] bookLogo;
    private double price;
    private int quantity;

}
