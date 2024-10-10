/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.cart;

import koikd.customtour.CustomTourDTO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
public class CartItem {
    private TourDTO tour; // for TourDTO
    private CustomTourDTO customTour; // for CustomTourDTO
    private int numberOfPeople;

    // Constructor for TourDTO
    public CartItem(TourDTO tour, int numberOfPeople) {
        this.tour = tour;
        this.customTour = null; // Ensure this is null
        this.numberOfPeople = numberOfPeople;
    }

    // Constructor for CustomTourDTO
    public CartItem(CustomTourDTO customTour, int numberOfPeople) {
        this.customTour = customTour;
        this.tour = null; // Ensure this is null
        this.numberOfPeople = numberOfPeople;
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
    }

    public double getTotalPrice() {
        double pricePerPerson = 0;

        if (tour != null) {
            pricePerPerson = tour.getTourPrice(); // Assuming TourDTO has this method
        } else if (customTour != null) {
            pricePerPerson = customTour.getQuotationPrice(); // Assuming CustomTourDTO has this method
        }

        return pricePerPerson * numberOfPeople;
    } 
}


