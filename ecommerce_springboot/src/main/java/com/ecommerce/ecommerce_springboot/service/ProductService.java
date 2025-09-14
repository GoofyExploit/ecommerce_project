package com.ecommerce.ecommerce_springboot.service;

import com.ecommerce.ecommerce_springboot.dao.ProductDAO;
import com.ecommerce.ecommerce_springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
