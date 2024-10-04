/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.booking;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Do Dat
 */
public class BookingDTO implements Serializable{
    private int bookingID;
    private int customerID;
    private int tourID;
    private String custName;
    private String custEmail;
    private Timestamp bookingDate;
    private String shippingAddress;
    private int quantity;
    private String status;
    private String tourType;

    public BookingDTO() {
    }   

    public BookingDTO(int bookingID, int customerID, int tourID, String custName, String custEmail, Timestamp bookingDate, String shippingAddress, int quantity, String status, String tourType) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.tourID = tourID;
        this.custName = custName;
        this.custEmail = custEmail;
        this.bookingDate = bookingDate;
        this.shippingAddress = shippingAddress;
        this.quantity = quantity;
        this.status = status;
        this.tourType = tourType;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }         

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
