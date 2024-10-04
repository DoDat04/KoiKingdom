/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.customtour;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADMIN LAM
 */
public class CustomTourDTO implements Serializable{
    private int requestID;
    private String farmName;
    private String koiTypeName;
    private String duration;
    private String quotationPrice;
    private int quantity;
    private Date startDate;
    private Date endDate;
    private String status;
    private String managerApprovalStatus;
    private String departureLocation;

    public CustomTourDTO() {
    }

    public CustomTourDTO(int requestID, String farmName, String koiTypeName, String duration, String quotationPrice, int quantity, Date startDate, Date endDate, String status, String managerApprovalStatus, String departureLocation) {
        this.requestID = requestID;
        this.farmName = farmName;
        this.koiTypeName = koiTypeName;
        this.duration = duration;
        this.quotationPrice = quotationPrice;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.managerApprovalStatus = managerApprovalStatus;
        this.departureLocation = departureLocation;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getKoiTypeName() {
        return koiTypeName;
    }

    public void setKoiTypeName(String koiTypeName) {
        this.koiTypeName = koiTypeName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuotationPrice() {
        return quotationPrice;
    }

    public void setQuotationPrice(String quotationPrice) {
        this.quotationPrice = quotationPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManagerApprovalStatus() {
        return managerApprovalStatus;
    }

    public void setManagerApprovalStatus(String managerApprovalStatus) {
        this.managerApprovalStatus = managerApprovalStatus;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    @Override
    public String toString() {
        return "CustomTourDTO{" + "requestID=" + requestID + ", farmName=" + farmName + ", koiTypeName=" + koiTypeName + ", duration=" + duration + ", quotationPrice=" + quotationPrice + ", quantity=" + quantity + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", managerApprovalStatus=" + managerApprovalStatus + ", departureLocation=" + departureLocation + '}';
    }
    
}
