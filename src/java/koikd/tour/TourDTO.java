/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tour;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Do Dat
 */
public class TourDTO implements Serializable{
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
    private int consultingID;


    public TourDTO() {
    }
    
    public TourDTO(int tourID, String tourName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, double tourRating, boolean status) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourImage = tourImage;
        this.tourRating = tourRating;
        this.status = status;
    }   

    public TourDTO(int tourID, String tourName, String koiTypeName, String farmName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, double tourRating, String tourDepartLoca) {
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

    public TourDTO(int tourID, String tourName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, boolean status, String tourDepartLoca) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourImage = tourImage;
        this.status = status;
        this.tourDepartLoca = tourDepartLoca;
    }
    
    public TourDTO(int tourID, String tourName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourDepartLoca) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourDepartLoca = tourDepartLoca;
    }
    
    public TourDTO(int tourID, String tourName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, boolean status, String tourDepartLoca, int consultingID) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.tourDepartLoca = tourDepartLoca;
        this.consultingID = consultingID;
        this.tourImage = tourImage;
    }
    
    public TourDTO(int tourID, String tourName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, boolean status, int consultingID) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.consultingID = consultingID;
    }
    
    public TourDTO(String tourName, String koiTypeName, String farmName, String duration, String description, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, boolean status, String tourDepartLoca, int consultingID) {
        this.tourName = tourName;
        this.koiTypeName = koiTypeName;
        this.farmName = farmName;
        this.duration = duration;
        this.description = description;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourImage = tourImage;
        this.status = status;
        this.tourDepartLoca = tourDepartLoca;
        this.consultingID = consultingID;
    }

    public TourDTO(int tourID, String tourName, String duration, double tourPrice, Timestamp startDate, Timestamp endDate, String tourImage, boolean status) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourImage = tourImage;
        this.status = status;
    }

    TourDTO(int tourID, String tourName, String image) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.tourImage = image;
    }
    
    public TourDTO(String tourName, Timestamp startDate, Timestamp endDate) {
        this.tourName = tourName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
         
    public String getTourDepartLoca() {
        return tourDepartLoca;
    }

    public void setTourDepartLoca(String tourDepartLoca) {
        this.tourDepartLoca = tourDepartLoca;
    }

    public double getTourRating() {
        return tourRating;
    }

    public void setTourRating(double tourRating) {
        this.tourRating = tourRating;
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
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getConsultingID() {
        return consultingID;
    }

    public void setConsultingID(int consultingID) {
        this.consultingID = consultingID;
    }
    
    
}
