package ru.gb.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.market.dtos.Cart;
import ru.gb.spring.market.entities.Product;
import ru.gb.spring.market.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден"));
        tempCart.add(product);
    }

    public void clearCurrentCart() {
        tempCart.clearCart();
    }

    public void deleteItemGroup(Long productId) {
        productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается удалить группу товаров с id: " + productId + " из корзины. Продукт не найден"));
        tempCart.deleteItemGroup(productId);
    }

    public void deleteItem(Long productId) {
        productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается удалить группу товаров с id: " + productId + " из корзины. Продукт не найден"));
        tempCart.deleteItem(productId);
    }
}
