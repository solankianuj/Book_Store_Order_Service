package com.example.bookOrderService.service;

import com.example.bookOrderService.exeption.OrderNotFound;
import com.example.bookOrderService.model.AddressModel;
import com.example.bookOrderService.model.OrderModel;
import com.example.bookOrderService.repository.OrderRepository;
import com.example.bookOrderService.service.mailService.MailServices;
import com.example.bookOrderService.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServices implements IOrderServices{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MailServices mailServices;
    @Autowired
    Token tokenUtil;
    @Override
    public Response placeOrder(String token, long cartId, AddressModel addressModel) {
        if (isUserPresent(token)!=null){
            OrderModel orderModel=new OrderModel();
            orderModel.setOrderDate(LocalDate.now());
            orderModel.setAddress(addressModel);
            orderModel.setUserId(isUserPresent(token).getUserId());
            orderModel.setCartId(isCartPresent(token, cartId).getCartId());
            Long bookId=isCartPresent(token, cartId).getBookId();
            orderModel.setBookId(bookId);
            orderModel.setQuantity(isCartPresent(token, cartId).getQuantity());
            orderModel.setPrice(isCartPresent(token, cartId).getTotalPrice());
            orderRepository.save(orderModel);
            mailServices.send(isUserPresent(token).getEmailId(),"Order Confirmation", "Your order of order id :"+ orderModel.getOrderId()+"is placed.\n"+orderModel);
            return new Response("Order is placed ",200,orderModel);
        }
        throw  new OrderNotFound(400,"User Not Found !");
    }

    @Override
    public Response cancelOrder(String token, long orderId) {
        if (isUserPresent(token)!=null){
            Optional<OrderModel> orderModel=orderRepository.findById(orderId);
            if (orderModel.isPresent()){
                orderModel.get().setCancel(true);
                orderRepository.save(orderModel.get());
                return new Response("Order deleted successfully",200,orderModel.get());
            }
            throw  new OrderNotFound(400,"Order Not Found !");
        }
        throw  new OrderNotFound(400,"User Not Found !");

    }

    @Override
    public Response getOrderOfUser(String token) {
        if (isUserPresent(token)!=null){
            Long userId= tokenUtil.decodeToken(token);
            List<OrderModel> listOfOrder = orderRepository.findAll().stream().filter(x->x.getUserId().equals(userId)).collect(Collectors.toList());
            return new Response("Fetching all Order of user",200,listOfOrder);
        }
        throw  new OrderNotFound(400,"User Not Found !");
    }

    @Override
    public Response getAllOrder() {
        List<OrderModel> listOfOrder =orderRepository.findAll().stream().filter(x-> x.isCancel()==false).collect(Collectors.toList());
        return new Response("Fetching all confirm orders",200,listOfOrder);
    }

    public BookStoreUser isUserPresent(String token){
        return restTemplate.getForObject("http://localhost:9091/user/verify/"+token,BookStoreUser.class);
    }
    public CartModel isCartPresent(String token,long cartId){
        return restTemplate.getForObject("http://localhost:9093/cart/getCart/{token}/{cartId}",CartModel.class,token,cartId);
    }

}
