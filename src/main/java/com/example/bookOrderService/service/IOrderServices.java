package com.example.bookOrderService.service;

import com.example.bookOrderService.model.AddressModel;
import com.example.bookOrderService.util.Response;

public interface IOrderServices {

    public Response placeOrder(String token, long cartId, AddressModel addressModel);
    public Response cancelOrder(String token,long orderId);
    public Response getOrderOfUser(String token);
    public Response getAllOrder();

}
