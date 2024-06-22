package com.demon.comrade_mart.service;

import com.demon.comrade_mart.dto.OrderRequestDTO;
import com.demon.comrade_mart.dto.ViewProductDTO;
import com.demon.comrade_mart.entity.Order;
import com.demon.comrade_mart.entity.Product;
import com.demon.comrade_mart.entity.Users;
import com.demon.comrade_mart.repository.OrderRepository;
import com.demon.comrade_mart.repository.ProductRepository;
import com.demon.comrade_mart.repository.UserRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(Long userId, OrderRequestDTO orderRequestDTO) {

        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(orderRequestDTO.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(orderRequestDTO.getQuantity());
        order.setPrice(orderRequestDTO.getPrice());
        order.setModeOfPayment(orderRequestDTO.getModeOfPayment());
        order.setDelivered(false);


        user.setUsername(orderRequestDTO.getUsername());
        user.setPhoneNumber(orderRequestDTO.getPhoneNumber());
        user.setAddress(orderRequestDTO.getAddress());
        user.setCity(orderRequestDTO.getCity());
        user.setState(orderRequestDTO.getState());
        order.setReceiveDate(Timestamp.from(Instant.now().plusSeconds(86400 * 7))); // Example: 7 days from now

        System.out.println(user);
        userRepository.save(user);

        product.setQuantity(product.getQuantity()-orderRequestDTO.getQuantity());
        System.out.println(product);
        productRepository.save(product);
        return orderRepository.save(order);
    }


    public List<ViewProductDTO> getOrderDetailsById(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(order -> new ViewProductDTO(
            order.getId(),
            order.getProduct().getName(),
            order.getPrice(),
            Long.valueOf(order.getQuantity()),
            order.getPrice() * order.getQuantity(),
            order.getReceiveDate() != null,
            order.getUser().getAddress(),
            order.getModeOfPayment()
        )).collect(Collectors.toList());
}

    @Scheduled(cron = "0 0 0 * * ?") // This runs at midnight every day
    public void updateOrderStatus() {
        Timestamp now = Timestamp.from(Instant.now());
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (order.getReceiveDate().before(now) && !order.isDelivered()) {
                order.setDelivered(true);
                orderRepository.save(order);
            }
        }
    }

    public List<Order> getOrdersByProductId(Long productId) {
        return orderRepository.findOrdersByProductId(productId);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }


    public String createOrder(Long userId, OrderRequestDTO orderRequest) {

        //try {
        RazorpayClient client = null;
        try {
            client = new RazorpayClient(razorpayKey, razorpaySecret);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

        JSONObject orderReq = new JSONObject();
            orderReq.put("amount", orderRequest.getPrice() * 100); // amount in paise
            orderReq.put("currency", "INR");
            orderReq.put("receipt", "order_rcptid_11");
        System.out.println("OrderService.createOrder"+orderReq);
        try {
            com.razorpay.Order order = client.orders.create(orderReq);

            System.out.println("payment paid successfully");
            return order.get("id");
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("orderRequest = " + orderRequest);
//        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
//
//        Order order = new Order();
//        order.setUser(user);
//        order.setProduct(product);
//        order.setQuantity(orderRequest.getQuantity());
//        order.setPrice(orderRequest.getPrice());
//        order.setModeOfPayment(orderRequest.getModeOfPayment());
//        order.setDelivered(false);
//
//
////        user.setUsername(orderRequestDTO.getUsername());
////        user.setPhoneNumber(orderRequestDTO.getPhoneNumber());
////        user.setAddress(orderRequestDTO.getAddress());
////        user.setCity(orderRequestDTO.getCity());
////        user.setState(orderRequestDTO.getState());
//        order.setReceiveDate(Timestamp.from(Instant.now().plusSeconds(86400 * 7))); // Example: 7 days from now
//
//        Order saved = orderRepository.save(order);
//        System.out.println(saved);
//        System.out.println("OrderService.createOrder"+saved.getId());


    }
}
