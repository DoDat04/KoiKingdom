/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tourbookingdetail;

import java.io.Serializable;
import java.sql.Timestamp;
import koikd.customer.CustomerDTO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
public class TourBookingDetailDTO implements Serializable {

    private int tourBookingDetailID;
    private int customerID;
    private String custName;
    private int tourID;
    private String tourName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String status;
    private String tourType;
    private int bookingID;
    private boolean feedbackStatus;
    private Timestamp cancelAt;
    private String reasonCancel;
    private CustomerDTO customerDTO;
    private TourDTO tourDTO;

    public TourBookingDetailDTO() {
    }

    public TourBookingDetailDTO(int tourBookingDetailID, String status, Timestamp cancelAt, String reasonCancel) {
        this.tourBookingDetailID = tourBookingDetailID;
        this.status = status;
        this.cancelAt = cancelAt;
        this.reasonCancel = reasonCancel;
    }  
    
    public TourBookingDetailDTO(int tourBookingDetailID, int customerID, int tourID, int quantity, double unitPrice, double totalPrice, String status, String tourType, int bookingID, boolean feedbackStatus) {
        this.tourBookingDetailID = tourBookingDetailID;
        this.customerID = customerID;
        this.tourID = tourID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.tourType = tourType;
        this.bookingID = bookingID;
        this.feedbackStatus = feedbackStatus;
    }

    public TourBookingDetailDTO(int tourBookingDetailID, int customerID, String custName, int tourID, String tourName, int quantity, double unitPrice, double totalPrice, String status, String tourType, int bookingID, boolean feedbackStatus) {
        this.tourBookingDetailID = tourBookingDetailID;
        this.customerID = customerID;
        this.custName = custName;
        this.tourID = tourID;
        this.tourName = tourName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.tourType = tourType;
        this.bookingID = bookingID;
        this.feedbackStatus = feedbackStatus;
    }

    public TourBookingDetailDTO(int tourBookingDetailID, int customerID, String custName, int tourID, String tourName, int quantity, double unitPrice, double totalPrice, String status, String tourType) {
        this.tourBookingDetailID = tourBookingDetailID;
        this.customerID = customerID;
        this.custName = custName;
        this.tourID = tourID;
        this.tourName = tourName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.tourType = tourType;
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
    
    
    public TourBookingDetailDTO(int tourBookingDetailID, double totalPrice, Timestamp cancelAt, CustomerDTO customerDTO, TourDTO tourDTO, String reasonCancel) {
        this.tourBookingDetailID = tourBookingDetailID;
      
        this.totalPrice = totalPrice;
        this.cancelAt = cancelAt;
        this.customerDTO = customerDTO;
        this.tourDTO = tourDTO;
        this.reasonCancel = reasonCancel;
    }

    public String getReasonCancel() {
        return reasonCancel;
    }

    public void setReasonCancel(String reasonCancel) {
        this.reasonCancel = reasonCancel;
    }

    public Timestamp getCancelAt() {
        return cancelAt;
    }

    public void setCancelAt(Timestamp cancelAt) {
        this.cancelAt = cancelAt;
    }
    
    public boolean isFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(boolean feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
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

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public TourDTO getTourDTO() {
        return tourDTO;
    }

    public void setTourDTO(TourDTO tourDTO) {
        this.tourDTO = tourDTO;
    }
    
    

    @Override
    public String toString() {
        return "TourBookingDetailDTO{" + "tourBookingDetailID=" + tourBookingDetailID + ", customerID=" + customerID + ", tourID=" + tourID + ", tourName=" + tourName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", status=" + status + '}';
    }

}
