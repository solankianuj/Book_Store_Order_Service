package com.example.bookOrderService.service;

import com.example.bookOrderService.dto.AddressDTO;
import com.example.bookOrderService.model.AddressModel;
import com.example.bookOrderService.repository.AddressRepository;
import com.example.bookOrderService.util.BookStoreUser;
import com.example.bookOrderService.util.Response;
import com.example.bookOrderService.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    Token tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response addAddress(String token, AddressDTO addressDTO) {
        if (isUserPresent(token)!=null){
            AddressModel addressModel= new AddressModel(addressDTO);
            addressRepository.save(addressModel);
            return new Response("Address added successfully",200,addressModel);
        }
        return null;
    }

    @Override
    public Response getAddress(String token, long addressId) {
        if (isUserPresent(token)!=null){
            Optional<AddressModel> addressModel=addressRepository.findById(addressId);
            if (addressModel.isPresent()){
                return new Response("Fetching Address",200,addressModel.get());
            }
            return null;
        }
        return null;
    }

    @Override
    public Response updateAddress(String token, AddressDTO addressDTO, long addressId) {
        if (isUserPresent(token)!=null) {
            Optional<AddressModel> addressModel = addressRepository.findById(addressId);
            if (addressModel.isPresent()) {
                addressModel.get().setAddress(addressDTO.getAddress());
                addressModel.get().setPhoneNumber(addressDTO.getPhoneNumber());
                addressModel.get().setEmailId(addressDTO.getEmailId());
                addressModel.get().setPinCode(addressDTO.getPinCode());
                addressModel.get().setTown(addressDTO.getTown());
                addressModel.get().setDistrict(addressDTO.getDistrict());
                addressModel.get().setState(addressDTO.getState());
                addressRepository.save(addressModel.get());
                return new Response("Fetching Address",200,addressModel.get());
            }
            return null;
        }
        return null;
    }

    @Override
    public Response deleteAddress(String token, long addressId) {
        if (isUserPresent(token)!=null){
            Optional<AddressModel> addressModel=addressRepository.findById(addressId);
            if (addressModel.isPresent()){
                addressRepository.delete(addressModel.get());
                return new Response(" Address deleted successfully.",200,addressModel.get());
            }
            return null;
        }
        return null;
    }

    public BookStoreUser isUserPresent(String token){
        return restTemplate.getForObject("http://localhost:9091/user/verify/"+token,BookStoreUser.class);
    }
}
