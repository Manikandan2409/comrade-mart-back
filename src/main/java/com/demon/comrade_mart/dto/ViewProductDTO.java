package com.demon.comrade_mart.dto;

public class ViewProductDTO {
    private Long orderid;
    private String productName;
    private Double productPrice;
    private Long quantity;
    private Double totalPrice;
    private boolean isDelivered;
    private String address;
    private String modeOfPayment;

    public ViewProductDTO() {}

    public ViewProductDTO(Long orderid, String productName, Double productPrice, Long quantity, Double totalPrice, boolean isDelivered, String address, String modeOfPayment) {
        this.orderid = orderid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.isDelivered = isDelivered;
        this.address = address;
        this.modeOfPayment = modeOfPayment;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    @Override
    public String toString() {
        return "ViewProductDTO{" +
                "orderid=" + orderid +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", isDelivered=" + isDelivered +
                ", address='" + address + '\'' +
                ", modeOfPayment='" + modeOfPayment + '\'' +
                '}';
    }
}
