package com.demon.comrade_mart.controller;

import com.demon.comrade_mart.entity.Product;
import com.demon.comrade_mart.utils.APIResponse;
import com.demon.comrade_mart.dto.ProductRequestDTO;
import com.demon.comrade_mart.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        System.out.println("product id"+id);
        Product product= productService.getProductById(id);
        System.out.println(product);
        return ResponseEntity.ok(product);
    }
@PostMapping(value = "/products/createProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<APIResponse> createProducts(
        HttpServletRequest request,
        @RequestParam("name") String name,
        @RequestParam("description") String description,
        @RequestParam("offer") Double offer,
        @RequestParam("beforeOfferPrice") Double beforeOfferPrice,
        @RequestParam("afterOfferPrice") Double afterOfferPrice,
        @RequestParam("quantity") Integer quantity,
        @RequestParam("type") String type,
        @RequestParam("image") MultipartFile image) {

    ProductRequestDTO productRequestDTO = new ProductRequestDTO();
    productRequestDTO.setName(name);
    productRequestDTO.setDescription(description);
    productRequestDTO.setOffer(offer);
    productRequestDTO.setBeforeOfferPrice(beforeOfferPrice);
    productRequestDTO.setAfterOfferPrice(afterOfferPrice);
    productRequestDTO.setQuantity(quantity);
    productRequestDTO.setType(type);
    System.out.println("ProductController.createProducts, image ="+ image.toString());
    productRequestDTO.setImage(image);

    APIResponse response = null;
    try {
        response = productService.createProduct(productRequestDTO);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return ResponseEntity.status(response.getStatus()).body(response);
}



//    @GetMapping("/products/getProducts")
//    ResponseEntity<APIResponse> getProducts(){
//        APIResponse response = productService.getAllProducts();
//        return  ResponseEntity.status(response.getStatus()).body(response);
//    }

    @GetMapping("/products/getProducts")
    List<Product> getProducts(){
       // ModelAndView mv = new ModelAndView("index");
        List<Product> imageList= productService.getAllProducts();
        System.out.println("Controller");

        imageList.stream().forEach(p->{
            System.out.println(p);
        });
        return  imageList;
        //mv.addObject("imageList", imageList);
        //return mv;
       // return
        //return  ResponseEntity.status(response.getStatus()).body(response);
    }



}
