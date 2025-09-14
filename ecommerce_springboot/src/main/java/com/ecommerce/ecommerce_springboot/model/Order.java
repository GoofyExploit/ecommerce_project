package com.ecommerce.ecommerce_springboot.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    @Override
    public String toString() {
        return "Order { " +
                "id=" + id +
                ", customerId=" + customerId +
                ", items=" + items +
                " }";
    }
}
