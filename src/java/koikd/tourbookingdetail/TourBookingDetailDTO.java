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
public class TourBookingDetailDTO implements Serializable {

    private int tourBookingDetailID;
    private int customerID;
    private int tourID;
    private String tourName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String status;

    public TourBookingDetailDTO() {
    }

    public TourBookingDetailDTO(int tourBookingDetailID, int customerID, int tourID, String tourName, int quantity, double unitPrice, double totalPrice, String status) {
        this.tourBookingDetailID = tourBookingDetailID;
        this.customerID = customerID;
        this.tourID = tourID;
        this.tourName = tourName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public TourBookingDetailDTO(int tourBookingDetailID, int customerID, int tourID, int quantity, double unitPrice, double totalPrice, String status) {
        this.tourBookingDetailID = tourBookingDetailID;
        this.customerID = customerID;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
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

    @Override
    public String toString() {
        return "TourBookingDetailDTO{" + "tourBookingDetailID=" + tourBookingDetailID + ", customerID=" + customerID + ", tourID=" + tourID + ", tourName=" + tourName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", status=" + status + '}';
    }

    
}
