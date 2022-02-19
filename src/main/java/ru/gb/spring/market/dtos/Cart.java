package ru.gb.spring.market.dtos;

import lombok.Data;
import ru.gb.spring.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        int quantityToAdd = 1;
        for (CartItem item : items) {
            if (item.getProductTitle().equals(product.getTitle())) {
                item.setQuantity(item.getQuantity() + quantityToAdd);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                recalculate();
                return;
            }
        }
        CartItem cartItem = new CartItem(product.getId(), product.getTitle(), quantityToAdd, product.getPrice(), (product.getPrice() * quantityToAdd));
        items.add(cartItem);
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void clearCart() {
        items.clear();
        recalculate();
    }

    public void deleteItemGroup(Long productId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                items.remove(item);
                break;
            }
        }
        recalculate();
    }

    public void deleteItem(Long productId) {
        int quantityToDelete = 1;
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() <= quantityToDelete) {
                    deleteItemGroup(productId);
                } else {
                    item.setQuantity(item.getQuantity() - quantityToDelete);
                    item.setPrice(item.getPricePerProduct() * item.getQuantity());
                }
                break;
            }
        }
        recalculate();
    }
}