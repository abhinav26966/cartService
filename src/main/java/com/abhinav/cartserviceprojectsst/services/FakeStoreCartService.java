package com.abhinav.cartserviceprojectsst.services;

import com.abhinav.cartserviceprojectsst.models.Cart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCartService implements CartServices{
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<Cart> getAllCarts() {
        ParameterizedTypeReference<List<Cart>> resTy = new ParameterizedTypeReference<>(){};

        String url = "https://fakestoreapi.com/carts";

        ResponseEntity<List<Cart>> resEn = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                resTy
        );

        List<Cart> ans = resEn.getBody();
        return ans == null ? new ArrayList<>() : ans;
    }
    @Override
    public Cart getSingleCart(int id) {
        String url = "https://fakestoreapi.com/carts/" + id;

        Cart ans = restTemplate.getForObject(
                url,
                Cart.class
        );
        return ans == null ? new Cart() : ans;
    }
    @Override
    public List<Cart> getInDateRange(LocalDate StartDate, LocalDate EndDate){
        String start = StartDate.toString();
        String end = EndDate.toString();

        String url = "https://fakestoreapi.com/carts?startdate=" + start + "&enddate=" + end;
        ParameterizedTypeReference<List<Cart>> resTy = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<Cart>> resEn = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                resTy
        );
        List<Cart> ans = resEn.getBody();
        return ans == null ? new ArrayList<>() : ans;
    }
    @Override
    public List<Cart> getUserCart(int userId){
        ParameterizedTypeReference<List<Cart>> responseType = new ParameterizedTypeReference<>(){};
        String url = "https://fakestoreapi.com/carts/user/" + userId;
        ResponseEntity<List<Cart>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType
        );
        List<Cart> ans = responseEntity.getBody();
        return ans == null ? new ArrayList<>() : ans;
    }
    @Override
    public Cart createCart(Cart cart) {
        String url = "https://fakestoreapi.com/carts";
        Cart ans = restTemplate.postForObject(
                url,
                cart,
                Cart.class
        );
        return ans == null ? new Cart() : ans;
    }
    @Override
    public Cart updateCart(int id, Cart cart) {
        String url = "https://fakestoreapi.com/carts/" + id;
        cart.setId(id);
        restTemplate.put(
                url,
                cart
        );
        return cart;
    }
    @Override
    public Cart deleteCart(int id) {
        String url = "https://fakestoreapi.com/carts/" + id;
        Cart ans = getSingleCart(id);
        restTemplate.delete(url);
        return ans;
    }
}
