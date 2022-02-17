package ru.gb.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.market.dtos.Cart;
import ru.gb.spring.market.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCurrentCart();
    }

    @GetMapping("/clear/{id}")
    public void deleteItemGroup(@PathVariable Long id) {
        cartService.deleteItemGroup(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        cartService.deleteItem(id);
    }
}
