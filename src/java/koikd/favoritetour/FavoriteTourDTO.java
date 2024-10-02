/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.favoritetour;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Do Dat
 */
public class FavoriteTourDTO implements Serializable{
    private int favoriteTourID;
    private int customerID;
    private int tourID;
    private String tourName;
    private String koiTypeName;
    private String farmName;
    private String duration;
    private String description;
    private double tourPrice;
    private Timestamp startDate;
    private Timestamp endDate;
    private String tourImage;
    private double tourRating;
    private boolean status;
    private String tourDepartLoca;

    public FavoriteTourDTO() {
    }

    public FavoriteTourDTO(int favoriteTourID, int customerID, int tourID) {
        this.favoriteTourID = favoriteTourID;
        this.customerID = customerID;
        this.tourID = tourID;
    }  

    public FavoriteTourDTO(int favoriteTourID, int customerID, int tourID, String tourName, String koiTypeName, String farmName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, double tourRating, String tourDepartLoca) {
        this.favoriteTourID = favoriteTourID;
        this.customerID = customerID;
        this.tourID = tourID;
        this.tourName = tourName;
        this.koiTypeName = koiTypeName;
        this.farmName = farmName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourImage = tourImage;
        this.tourRating = tourRating;
        this.tourDepartLoca = tourDepartLoca;
    }  

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getKoiTypeName() {
        return koiTypeName;
    }

    public void setKoiTypeName(String koiTypeName) {
        this.koiTypeName = koiTypeName;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getTourImage() {
        return tourImage;
    }

    public void setTourImage(String tourImage) {
        this.tourImage = tourImage;
    }

    public double getTourRating() {
        return tourRating;
    }

    public void setTourRating(double tourRating) {
        this.tourRating = tourRating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTourDepartLoca() {
        return tourDepartLoca;
    }

    public void setTourDepartLoca(String tourDepartLoca) {
        this.tourDepartLoca = tourDepartLoca;
    }    

    public int getFavoriteTourID() {
        return favoriteTourID;
    }

    public void setFavoriteTourID(int favoriteTourID) {
        this.favoriteTourID = favoriteTourID;
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
}
