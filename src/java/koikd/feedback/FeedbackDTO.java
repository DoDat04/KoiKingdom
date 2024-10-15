/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.feedback;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class FeedbackDTO {

    private int feedbackID;
    private int customerID;
    private int rating;
    private String feedbackText;
    private Timestamp createdAt;
    private int tourID;
   private int bookingID;
   
    public FeedbackDTO() {
    }

    public FeedbackDTO(int feedbackID, int customerID, int rating, String feedbackText, Timestamp createdAt, int tourID) {
        this.feedbackID = feedbackID;
        this.customerID = customerID;
        this.rating = rating;
        this.feedbackText = feedbackText;
        this.createdAt = createdAt;
        this.tourID = tourID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public Date getCreatedAt() {
        return createdAt;
    }    

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

}
