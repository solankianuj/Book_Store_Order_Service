package com.example.bookOrderService.model;

import com.example.bookOrderService.dto.AddressDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="addressdatadb")
@Data
public class AddressModel {

    @Id
    @GenericGenerator(name = "addressdatadb",strategy = "increment")
    @GeneratedValue(generator = "addressdatadb")
    private Long addressId;
    private String address;
    private String phoneNumber;
    private String emailId;
    private int pinCode;
    private String town;
    private String district;
    private String state;

    public AddressModel(AddressDTO addressDTO) {
        this.address=addressDTO.getAddress();
        this.phoneNumber=addressDTO.getPhoneNumber();
        this.emailId=addressDTO.getEmailId();
        this.pinCode=addressDTO.getPinCode();
        this.town=addressDTO.getTown();
        this.district=addressDTO.getDistrict();
        this.state=addressDTO.getState();
    }

    public AddressModel() {

    }
}
