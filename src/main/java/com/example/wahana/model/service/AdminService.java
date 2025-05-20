package com.example.wahana.model.service;

import com.example.wahana.model.entity.User;
import com.example.wahana.model.entity.Pemesanan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final List<User> users = new ArrayList<>();
    private final List<Pemesanan> allOrders = new ArrayList<>();

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public List<Pemesanan> getAllOrders() {
        return new ArrayList<>(allOrders);
    }

    public void updateOrderStatus(Long orderId, String newStatus) {
        for (Pemesanan p : allOrders) {
            if (p.getId().equals(orderId)) {
                p.setStatus(newStatus);
                break;
            }
        }
    }

    public void cancelOrder(Long orderId) {
        allOrders.removeIf(p -> p.getId().equals(orderId));
    }

    // Bisa ditambahkan metode tambahan sesuai kebutuhan admin
}
