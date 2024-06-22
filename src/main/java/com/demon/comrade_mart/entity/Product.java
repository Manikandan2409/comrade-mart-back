package com.demon.comrade_mart.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double offer;

    @Column(name = "before_offer_price")
    private Double beforeOfferPrice;

    @Column(name = "after_offer_price")
    private Double afterOfferPrice;

    private Integer quantity;

    private  String type;
    private LocalDateTime timestamp;
   // @JsonIgnore
//    public Blob getImage() {
//        return image;
//    }

    public byte[] getImage(byte[] bytes) {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    //   // public void setImage(Blob image) {
//        this.image = image;
//    }
   // @JsonIgnore
    //private Blob image;
@Lob
@Column(name = "image",columnDefinition = "BLOB")
//private byte[] image;

@JsonIgnore
private byte[] image;

    // Getters and setters

    @Transient
    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(this.image);
    }
    public Product() {
        this.timestamp = LocalDateTime.now();
    }

    public Product(Long id, String name, String description, Double offer, Double beforeOfferPrice, Double afterOfferPrice, Integer quantity, String type, LocalDateTime timestamp, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.offer = offer;
        this.beforeOfferPrice = beforeOfferPrice;
        this.afterOfferPrice = afterOfferPrice;
        this.quantity = quantity;
        this.type = type;
        this.image = image;
        this.timestamp = LocalDateTime.now();
    }
//    @JsonProperty("imageBase64")
//    public byte[] getImageBytes() {
//        try {
//            if (image != null) {
//                int blobLength = (int) image.length();
//                byte[] bytes = image.getBytes(1, blobLength);
//                return bytes;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", offer=" + offer +
                ", beforeOfferPrice=" + beforeOfferPrice +
                ", afterOfferPrice=" + afterOfferPrice +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ",type="+type+
                "image:"+image+
                '}';
    }
}
