package com.demon.comrade_mart.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

public class ProductRequestDTO {

    private  Long id;
    private String name;
    private String description;
    private Double offer;
    private Double beforeOfferPrice;
    private Double afterOfferPrice;
    private Integer quantity;
    private  String type;
    private MultipartFile image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ProductRequestDTO() {
    }

    public ProductRequestDTO(Long id, String name, String description, Double offer, Double beforeOfferPrice, Double afterOfferPrice, Integer quantity, String type, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.offer = offer;
        this.beforeOfferPrice = beforeOfferPrice;
        this.afterOfferPrice = afterOfferPrice;
        this.quantity = quantity;
        this.type=type;
        this.image=image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Double getBeforeOfferPrice() {
        return beforeOfferPrice;
    }

    public void setBeforeOfferPrice(Double beforeOfferPrice) {
        this.beforeOfferPrice = beforeOfferPrice;
    }

    public Double getAfterOfferPrice() {
        return afterOfferPrice;
    }

    public void setAfterOfferPrice(Double afterOfferPrice) {
        this.afterOfferPrice = afterOfferPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", offer=" + offer +
                ", beforeOfferPrice=" + beforeOfferPrice +
                ", afterOfferPrice=" + afterOfferPrice +
                ", quantity=" + quantity +
                ", image byte"+image.toString() +
                '}';
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
