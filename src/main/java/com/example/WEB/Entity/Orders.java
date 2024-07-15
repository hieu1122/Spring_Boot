package com.example.WEB.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
//    @JsonBackReference
    @JsonIgnore
    private Customer customer;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @Column(name = "total_amount")
    private int totalAmount;

    @OneToOne(mappedBy = "order"
            , fetch = FetchType.LAZY)
//    @JsonManagedReference
    private Payment payments;

    @OneToOne(mappedBy = "order",
            fetch = FetchType.LAZY)
//    @JsonManagedReference
    private Shipment shipments;

    @OneToMany(mappedBy = "orders",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<OrderItem> orderItems;

//    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "orders")
//    @JsonIgnore
//    private List<Product> products;

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    public Orders(int orderId, LocalDate date, String status, int totalAmount) {
    }

    public Orders() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }

    public Shipment getShipments() {
        return shipments;
    }

    public void setShipments(Shipment shipments) {
        this.shipments = shipments;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
