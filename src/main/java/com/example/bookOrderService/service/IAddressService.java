package com.example.bookOrderService.service;

import com.example.bookOrderService.dto.AddressDTO;
import com.example.bookOrderService.util.Response;

public interface IAddressService {

    public Response addAddress(String token, AddressDTO addressDTO);
    public Response getAddress(String token, long addressId);
    public Response updateAddress(String token, AddressDTO addressDTO, long addressId);
    public Response deleteAddress(String token,long addressId);
}
