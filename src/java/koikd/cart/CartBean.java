/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import koikd.customtour.CustomTourDTO;
import koikd.koi.KoiDTO;
import koikd.tour.TourDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartBean implements Serializable {
    private Map<Integer, CartItem> items;

    public CartBean() {
        this.items = new HashMap<>();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // Thêm một TourDTO vào giỏ hàng
    public void addItemToCart(TourDTO tour, int numberOfPeople) {
        if (tour == null || numberOfPeople <= 0) {
            return;
        }

        int tourID = tour.getTourID(); 

        if (this.items.containsKey(tourID)) {
            CartItem existingItem = this.items.get(tourID);
            existingItem.setNumberOfPeople(existingItem.getNumberOfPeople() + numberOfPeople);
        } else {
            this.items.put(tourID, new CartItem(tour, numberOfPeople));
        }
    }

    public void addKoiToCart(KoiDTO koi, int quantity) {
        if (koi == null || quantity <= 0) {
            return;
        }

        int koiID = koi.getKoiID(); 

        if (this.items.containsKey(koiID)) {
            CartItem existingItem = this.items.get(koiID);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            this.items.put(koiID, new CartItem(koi, quantity));
        }
    }

    public void addCustomTourToCart(CustomTourDTO customTour, int numberOfPeople) {
        if (customTour == null || numberOfPeople <= 0) {
            return;
        }

        int requestID = customTour.getRequestID(); 

        if (this.items.containsKey(requestID)) {
            CartItem existingItem = this.items.get(requestID);
            existingItem.setNumberOfPeople(existingItem.getNumberOfPeople() + numberOfPeople);
        } else {
            this.items.put(requestID, new CartItem(customTour, numberOfPeople));
        }
    }

    public void removeItemFromCart(TourDTO tour, int numberOfPeople) {
        if (tour == null || numberOfPeople <= 0) {
            return;
        }

        int tourID = tour.getTourID();
        if (this.items.containsKey(tourID)) {
            this.items.remove(tourID);
        }
    }
    
    public void removeKoiFromCart(KoiDTO koi, int quantity) {
        if (koi == null || quantity <= 0) {
            return;
        }

        int koiID = koi.getKoiID();
        if (this.items.containsKey(koiID)) {
            this.items.remove(koiID);
        }
    }
    
    public int getTotalQuantityKoi() {
        int totalKoi = 0;
        for (CartItem item : items.values()) {
            totalKoi += item.getQuantity();
        }
        return totalKoi;
    }

    // Lấy tổng số người cho tất cả các mục trong giỏ hàng
    public int getTotalNumberOfPeople() {
        int totalPeople = 0;
        for (CartItem item : items.values()) {
            totalPeople += item.getNumberOfPeople();
        }
        return totalPeople;
    }

    // Lấy tổng giá cho tất cả các mục trong giỏ hàng
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items.values()) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    // Lấy tổng số lượng mục trong giỏ hàng
    public int getTotalQuantity() {
        return items.size();
    }
}
