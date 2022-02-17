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
        int index = items.size();
        int quantity = 1;
        for (CartItem item : items) {
            if (item.getProductTitle().equals(product.getTitle())) {
                quantity = item.getQuantity() + 1;
                index = items.indexOf(item);
                items.remove(item);
                break;
            }
        }
        CartItem cartItem = new CartItem(product.getId(), product.getTitle(), quantity, product.getPrice(), (product.getPrice() * quantity));
        items.add(index, cartItem);
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
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                int quantity = item.getQuantity();
                if (quantity == 1) {
                    deleteItemGroup(productId);
                } else {
                    quantity--;
                    int index = items.indexOf(item);
                    items.remove(item);
                    CartItem cartItem = new CartItem(item.getProductId(), item.getProductTitle(), quantity, item.getPricePerProduct(), (item.getPricePerProduct() * quantity));
                    items.add(index, cartItem);
                }
                break;
            }
        }
        recalculate();
    }
}