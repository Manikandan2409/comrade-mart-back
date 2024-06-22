package com.demon.comrade_mart.service;

import com.demon.comrade_mart.entity.Cart;
import com.demon.comrade_mart.entity.Product;
import com.demon.comrade_mart.entity.Users;
import com.demon.comrade_mart.repository.CartRepository;
import com.demon.comrade_mart.repository.ProductRepository;
import com.demon.comrade_mart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long userId, Long productId, int quantity) {
        Optional<Cart> existingCartItem = cartRepository.findByUserIdAndProductId(userId, productId);

        if (existingCartItem.isPresent()) {
            Cart cart = existingCartItem.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cartRepository.save(cart);
        } else {
            Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    public List<Product> getCartItems(Long userId) {
        List<Cart> cartItems=cartRepository.findByUserId(userId);
        List<Product> products=cartItems.stream()
                .map(Cart::getProduct)
                .collect(Collectors.toList());

        products.stream().forEach(product -> System.out.println(product));
        return  products;
    }

    public int getCartItemCount(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        return cartItems.stream().mapToInt(Cart::getQuantity).sum();
    }


    public void removeFromCart(Long userId, Long productId) {
        System.out.println("CartService.removeFromCart");
        if (isCartItemPresent(userId, productId)) {
            cartRepository.removeByUserIdAndProductId(userId, productId);
        } else {
            throw new RuntimeException("Cart item not found");
        }
    }

    public  boolean isCartItemPresent(Long userId,Long productId){
        System.out.println("CartService.isCartItemPresent");
        return cartRepository.existsByUserIdAndProductId(userId,productId);
    }
}

