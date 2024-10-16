/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import koikd.customtour.CustomTourDTO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
public class CartBean implements Serializable {
    private Map<Integer, CartItem> items; 

    public CartBean() {
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

    public void addItemToCartt(CustomTourDTO customTour, int numberOfPeople) {
        if (customTour == null || numberOfPeople <= 0) {
            return;
        }

        int tourID = customTour.getRequestID(); // Assuming CustomTourDTO has getTourID() method

        if (this.items.containsKey(tourID)) {
            CartItem existingItem = this.items.get(tourID);
            //existingItem.setNumberOfPeople(existingItem.getNumberOfPeople() + numberOfPeople);
        } else {
            this.items.put(tourID, new CartItem(customTour, numberOfPeople));
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
