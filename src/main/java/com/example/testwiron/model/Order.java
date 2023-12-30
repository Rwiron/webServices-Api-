package com.example.testwiron.model;

import jakarta.persistence.*;

import java.util.List;

import java.util.Map;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @ElementCollection
    private List<Long> productIds;

    @ElementCollection
    private Map<Long, Integer> quantities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Map<Long, Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<Long, Integer> quantities) {
        this.quantities = quantities;
    }
}
