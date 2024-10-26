/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import koikd.customtour.CustomTourDTO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class CartItem {
    private TourDTO tour; 
    private CustomTourDTO customTour; 
    private int numberOfPeople;
    private double totalPrice; 

    public CartItem() {
    }

    public CartItem(TourDTO tour, int numberOfPeople) {
        this.tour = tour;
        this.customTour = null; 
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = calculateTotalPrice(); 
    }

    public CartItem(CustomTourDTO customTour, int numberOfPeople) {
        this.customTour = customTour;
        this.tour = null; 
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = calculateTotalPrice(); 
    }

    public TourDTO getTour() {
        return tour;
    }

    public CustomTourDTO getCustomTour() {
        return customTour;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = calculateTotalPrice(); 
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        double pricePerPerson = 0;

        if (tour != null) {
            pricePerPerson = tour.getTourPrice(); 
        } else if (customTour != null) {
            pricePerPerson = customTour.getQuotationPrice(); 
        }

        return pricePerPerson * numberOfPeople;
    }
}



