/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.cart;

import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
public class CartItem {
    private TourDTO tour;
    private int numberOfPeople;

    public CartItem(TourDTO tour, int numberOfPeople) {
        this.tour = tour;
        this.numberOfPeople = numberOfPeople;
    }    

    public TourDTO getTour() {
        return tour;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTotalPrice() {
        return tour.getTourPrice() * numberOfPeople; 
    }   
}
