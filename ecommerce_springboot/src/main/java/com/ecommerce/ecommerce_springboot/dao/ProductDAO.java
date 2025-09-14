package com.ecommerce.ecommerce_springboot.dao;

import com.ecommerce.ecommerce_springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addProduct(Product product) {
        String sql = "INSERT INTO products(name, category, price, stock) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getPrice(), product.getStock());
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ), id);
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, stock = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getCategory(), 
                product.getPrice(), product.getStock(), product.getId());
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
