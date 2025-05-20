// package com.example.wahana.model.service;

// import com.example.wahana.model.entity.Pemesanan;
// import com.example.wahana.model.entity.Wahana;
// import java.util.List;

// public interface PemesananService {
//     // Keranjang belanja
//     Pemesanan addToKeranjang(String userId, Wahana wahana, int jumlah);
//     List<Pemesanan> getPemesananByUser(String userId);
//     void updateJumlahPemesanan(Long pemesananId, int jumlah);
//     void removeFromKeranjang(Long pemesananId);
//     void checkout(String userId);
    
//     // Method lainnya tetap sama...
//     Pemesanan addToCart(String userId, Wahana wahana, int jumlah);
//     List<Pemesanan> getCartItems(String userId);
//     void updateCartItem(Long pemesananId, int jumlah);
//     void removeFromCart(Long pemesananId);
//     void clearCart(String userId);
    
//     // Proses pemesanan
//     Pemesanan processOrder(Long pemesananId, String metodePembayaran);
//     List<Pemesanan> completeOrder(String userId, String metodePembayaran);
    
//     // History pemesanan
//     List<Pemesanan> getOrderHistory(String userId);
//     List<Pemesanan> getAllCompletedOrders();
//     Pemesanan getOrderDetail(Long pemesananId);
    
//     // Admin functions
//     void cancelOrder(Long pemesananId);
//     void updateOrderStatus(Long pemesananId, String status);
// 