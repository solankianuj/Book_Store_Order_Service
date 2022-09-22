package com.example.bookOrderService.controller;

import com.example.bookOrderService.dto.AddressDTO;
import com.example.bookOrderService.service.AddressService;
import com.example.bookOrderService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/addAddress")
    public ResponseEntity<Response> addingAddress(@RequestHeader String token, @RequestBody AddressDTO addressDTO){
        Response response=addressService.addAddress(token,addressDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAddress")
    public ResponseEntity<Response> gettingAddress(@RequestHeader String token, @RequestParam("addressId") long addressId){
        Response response=addressService.getAddress(token,addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<Response> updatingAddress(@RequestHeader String token, @RequestBody AddressDTO addressDTO,@RequestParam("addressId") long addressId ){
        Response response=addressService.updateAddress(token,addressDTO,addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deletingAddress")
    public ResponseEntity<Response> deleteAddress(@RequestHeader String token, @RequestParam("addressId") long addressId){
        Response response=addressService.deleteAddress(token,addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
