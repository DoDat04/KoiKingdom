/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
public class CartBeanBook implements Serializable{
    private Map<Integer, CartItem> items; 

    public CartBeanBook() {
        this.items = new HashMap<>(); 
    }   

    public Map<Integer, CartItem> getItems() {
        return items;
    }       

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
    
    public int getTotalNumberOfPeople() {
        int totalPeople = 0;
        for (CartItem item : items.values()) {
            totalPeople += item.getNumberOfPeople();
        }
        return totalPeople;
    }
    
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items.values()) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
    
    public int getTotalQuantity() {
        return items.size();
    }
}
