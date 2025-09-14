package com.ecommerce.ecommerce_springboot.dao;

import com.ecommerce.ecommerce_springboot.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addOrder(Order order) {
        String sql = "INSERT INTO orders(customer_id) VALUES (?)";
        jdbcTemplate.update(sql, order.getCustomerId());

    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customer_id"));
            return order;
        });
    }

    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customer_id"));
            return order;
        }, id);
    }

    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET customer_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, order.getCustomerId(), order.getId());
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
