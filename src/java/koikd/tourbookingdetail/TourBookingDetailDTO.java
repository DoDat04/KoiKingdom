/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tourbookingdetail;

import java.io.Serializable;

/**
 *
 * @author Do Dat
 */
public class TourBookingDetailDTO implements Serializable{
    private int tourBookingDetailID;
    private int bookingID;
    private int tourID;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String status;

    public TourBookingDetailDTO(int bookingID, int tourID, int quantity, double unitPrice, double totalPrice, String status) {
        this.bookingID = bookingID;
        this.tourID = tourID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }     

    public int getTourBookingDetailID() {
        return tourBookingDetailID;
    }

    public void setTourBookingDetailID(int tourBookingDetailID) {
        this.tourBookingDetailID = tourBookingDetailID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
