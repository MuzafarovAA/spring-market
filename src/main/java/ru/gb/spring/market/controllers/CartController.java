package ru.gb.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.market.entities.Product;
import ru.gb.spring.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private List<Product> cart = new ArrayList<>();

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id) {
        Product product = productService.findById(id).get();
        cart.add(product);
    }

    @GetMapping("")
    public List<Product> showAllProducts() {
        return cart;
    }

}
