package com.demon.comrade_mart.service;

import com.demon.comrade_mart.utils.APIResponse;
import com.demon.comrade_mart.dto.ProductRequestDTO;
import com.demon.comrade_mart.entity.Product;
import com.demon.comrade_mart.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;






    public List<Product> getAllProducts() {
        //APIResponse api =new APIResponse();
        List<Product> products = productRepo.findAll();
        return  products;
    }


    public Product getProductById(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    public APIResponse createProduct(ProductRequestDTO productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setOffer(productDto.getOffer());
        product.setBeforeOfferPrice(productDto.getBeforeOfferPrice());
        product.setAfterOfferPrice(productDto.getAfterOfferPrice());
        product.setQuantity(productDto.getQuantity());
        product.setType(productDto.getType());
        System.out.println("ProductService.createProduct"+ productDto);
        for (byte b:productDto.getImage().getBytes()
             ) {
            System.out.print(b);

        }
        product.setImage(productDto.getImage().getBytes());
        System.out.println("ProductService.createProduct , product="+product);
        System.out.println("ProductService.createProduct, productdto="+productDto);

        Product savedProduct = productRepo.save(product);
        APIResponse api = new APIResponse();
        api.setData("Product Inserted Successfully: " + LocalDateTime.now());
        return api;
    }

}
