package com.demon.comrade_mart.controller;

import com.demon.comrade_mart.dto.OrderRequestDTO;
import com.demon.comrade_mart.dto.ViewProductDTO;
import com.demon.comrade_mart.entity.Order;
import com.demon.comrade_mart.service.OrderService;
import com.demon.comrade_mart.utils.JwtUtils;
import com.demon.comrade_mart.utils.PdfGenerator;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/place")
//    public Order placeOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequestDTO orderRequestDTO) {
//        Long userId = JwtUtils.extractUserIdFromToken(token);
//        System.out.println(orderRequestDTO);
//        return orderService.placeOrder(userId, orderRequestDTO);
//    }
//
//    @GetMapping("/view-ordered-products")
//    public List<ViewProductDTO> getUserPlacedOrder(@RequestHeader("Authorization")String token){
//        Long userId = JwtUtils.extractUserIdFromToken(token);
//        System.out.println("userid"+userId);
//        return  orderService.getOrderDetailsById(userId);
//    }
//
//
//    @Autowired
//    private PdfGenerator pdfGenerator;
//
//    @GetMapping("/orders/pdf")
//    public ResponseEntity<byte[]> getOrderDetailsPdf(@RequestHeader("Authorization") String token) {
//        Long userId = JwtUtils.extractUserIdFromToken(token);
//        List<ViewProductDTO> orderDetails = orderService.getOrderDetailsById(userId);
//        System.out.println("OrderController.getOrderDetailsPdf"+orderDetails);
//        try {
//            byte[] pdf = pdfGenerator.generatePdf(orderDetails);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment; filename="+userId+"OrderDetails.pdf");
//            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
////    @GetMapping("/get-order")
////    public Optional<List<Order>> getOrderDetails(@RequestHeader("Authorization") String token){
////        Long userId = JwtUtils.extractUserIdFromToken(token);
////
////    }
//
//}
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PdfGenerator pdfGenerator;

    @PostMapping("/place")
    public Order placeOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequestDTO orderRequestDTO) {
        Long userId = JwtUtils.extractUserIdFromToken(token);
        return orderService.placeOrder(userId, orderRequestDTO);
    }

    @GetMapping("/view-ordered-products")
    public List<ViewProductDTO> getUserPlacedOrder(@RequestHeader("Authorization") String token) {
        Long userId = JwtUtils.extractUserIdFromToken(token);
        return orderService.getOrderDetailsById(userId);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getOrderDetailsPdf(@RequestHeader("Authorization") String token) {
        Long userId = JwtUtils.extractUserIdFromToken(token);
        List<ViewProductDTO> orderDetails = orderService.getOrderDetailsById(userId);
        try {
            byte[] pdf = pdfGenerator.generatePdf(orderDetails);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + userId + "OrderDetails.pdf");
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>>  createOrderByBankTransfer(@RequestHeader("Authorization") String token, @RequestBody OrderRequestDTO orderRequest) {
        Long userId= JwtUtils.extractUserIdFromToken(token);
        String id=orderService.createOrder(userId,orderRequest);
        Map<String, String> response = Collections.singletonMap("id", id);
        return ResponseEntity.ok(response);
    }


}
