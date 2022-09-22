package com.example.bookOrderService.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String address;
    private String phoneNumber;
    private String emailId;
    private int pinCode;
    private String town;
    private String district;
    private String state;
}
