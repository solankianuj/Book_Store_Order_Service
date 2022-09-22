package com.example.bookOrderService.controller;

import com.example.bookOrderService.model.AddressModel;
import com.example.bookOrderService.service.OrderServices;
import com.example.bookOrderService.util.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    @PostMapping("/placeOrder")
    public ResponseEntity<Response> placedOrder(@RequestHeader String token, @RequestParam("cartId") long cartId, @RequestBody AddressModel addressModel){
        Response response=orderServices.placeOrder(token, cartId, addressModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/cancelOrder")
    public ResponseEntity<Response> cancelOrder(@RequestHeader String token, @RequestParam("orderId") long orderId){
        Response response=orderServices.cancelOrder(token,orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getUserOrder")
    public ResponseEntity<Response> userOrders(@RequestHeader String token){
        Response response=orderServices.getOrderOfUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<Response> getAllOrders(){
        Response response=orderServices.getAllOrder();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
