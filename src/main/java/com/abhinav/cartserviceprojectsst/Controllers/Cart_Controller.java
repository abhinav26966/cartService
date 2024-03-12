package com.abhinav.cartserviceprojectsst.Controllers;

import com.abhinav.cartserviceprojectsst.models.Cart;
import com.abhinav.cartserviceprojectsst.services.FakeStoreCartService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class Cart_Controller {
    private final FakeStoreCartService cartService;

    public Cart_Controller(FakeStoreCartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") int id) {
        return cartService.getSingleCart(id);
    }
    @GetMapping("/carts/date/{start}/{end}")
    public List<Cart> getInDateRange(@PathVariable("start") String start, @PathVariable("end") String end) {
        LocalDate sDate = LocalDate.parse(start);
        LocalDate eDate = LocalDate.parse(end);
        return cartService.getInDateRange(sDate, eDate);
    }
    @GetMapping("/carts/user/{userId}")
    public List<Cart> getUserCart(@PathVariable("userId") int userId) {
        return cartService.getUserCart(userId);
    }
    @PostMapping("/carts")
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }
    @PutMapping("/carts/{id}")
    public Cart updateCart(@PathVariable("id") int id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }
    @DeleteMapping("/carts/{id}")
    public Cart deleteCart(@PathVariable("id") int id) {
        return cartService.deleteCart(id);
    }
}
