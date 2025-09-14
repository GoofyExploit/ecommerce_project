package com.ecommerce.ecommerce_springboot.dao;

import com.ecommerce.ecommerce_springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user) {
        String sql = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole());
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                ), id);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    ), username);
        } catch (Exception e) {
            return null; // User not found
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.getId());
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
