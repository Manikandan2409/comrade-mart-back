package com.demon.comrade_mart.controller;

import com.demon.comrade_mart.dto.AddToCartRequest;
import com.demon.comrade_mart.entity.Cart;
import com.demon.comrade_mart.entity.Product;
import com.demon.comrade_mart.service.CartService;
import com.demon.comrade_mart.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestHeader("Authorization") String token,
                                            @RequestBody AddToCartRequest addToCartRequest) {
        // Extract productId and quantity from AddToCartRequest object
        Long productId = addToCartRequest.getProductId();
        int quantity = addToCartRequest.getQuantity();

        // Assuming userId is hardcoded for testing, you'll replace it with JWT token logic
        Long userId = JwtUtils.extractUserIdFromToken(token);;

        cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @GetMapping("/items")
    public ResponseEntity<List<Product>> getCartItems(@RequestHeader("Authorization") String token) {
        Long userId = JwtUtils.extractUserIdFromToken(token);
        List<Product> productItems = cartService.getCartItems(userId);
        //cartItems.stream().forEach(cart -> System.out.println(cart));
        return ResponseEntity.ok(productItems);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCartItemCount(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        Long userId = JwtUtils.extractUserIdFromToken(token);
        int itemCount = cartService.getCartItemCount(userId);
        return ResponseEntity.ok(itemCount);
    }

//    @DeleteMapping("/remove")
//    public ResponseEntity<String> removeFromCart(@RequestHeader("Authorization") String token,
//                                                 @RequestBody AddToCartRequest removeFromCartRequest) {
//        Long productId = removeFromCartRequest.getProductId();
//        Long userId = JwtUtils.extractUserIdFromToken(token);
//
//        cartService.removeFromCart(userId, productId);
//        return ResponseEntity.ok("Product removed from cart successfully");
//    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestHeader("Authorization") String token,
                                                 @RequestBody AddToCartRequest removeFromCartRequest) {
        Long productId = removeFromCartRequest.getProductId();
        Long userId = JwtUtils.extractUserIdFromToken(token);
        System.out.println("Product id="+productId+ "user id="+ userId);
        if (cartService.isCartItemPresent(userId, productId)) {
            cartService.removeFromCart(userId, productId);
            return ResponseEntity.ok("Product removed from cart successfully");
        } else {
            return ResponseEntity.status(404).body("Cart item not found");
        }
    }


}

