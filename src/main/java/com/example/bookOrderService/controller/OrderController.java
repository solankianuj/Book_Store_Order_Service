package com.example.bookOrderService.controller;

import com.example.bookOrderService.model.AddressModel;
import com.example.bookOrderService.service.OrderServices;
import com.example.bookOrderService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Purpose-Creating order service operation APIs.
 * @author anuj solanki
 * @date 21/09/2022
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    /**
     * Purpose-API to  place order.
     * @param token
     * @param cartId
     * @param addressModel
     * @return
     */
    @PostMapping("/placeOrder")
    public ResponseEntity<Response> placedOrder(@RequestHeader String token, @RequestParam("cartId") long cartId, @RequestBody AddressModel addressModel){
        Response response=orderServices.placeOrder(token, cartId, addressModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to  cancel order.
     * @param token
     * @param orderId
     * @return
     */
    @DeleteMapping("/cancelOrder")
    public ResponseEntity<Response> cancelOrder(@RequestHeader String token, @RequestParam("orderId") long orderId){
        Response response=orderServices.cancelOrder(token,orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetch user's orders.
     * @param token
     * @return
     */
    @GetMapping("/getUserOrder")
    public ResponseEntity<Response> userOrders(@RequestHeader String token){
        Response response=orderServices.getOrderOfUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetch all orders.
     * @return
     */
    @GetMapping("/getAllOrders")
    public ResponseEntity<Response> getAllOrders(){
        Response response=orderServices.getAllOrder();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
