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
    private int customerID;
    private String custName;
    private String farmName;
    private String koiTypeName;
    private String duration;
    private double quotationPrice;
    private int quantity;
    private Date startDate;
    private Date endDate;
    private String status;
    private String managerApprovalStatus;
    private String departureLocation;
    private String image;
    private boolean checked;
    private String detailRejected;

    public CustomTourDTO() {
    }

    // Constructor for Customer
    public CustomTourDTO(int requestID, int customerID, String custName, String farmName, String koiTypeName, String duration, double quotationPrice, int quantity, Date startDate, Date endDate, String status, String managerApprovalStatus, String departureLocation, String image) {
        this.requestID = requestID;
        this.customerID = customerID;
        this.custName = custName;
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
        this.image = image;
    } 
    
    //Constructor for Sales, Manager
    public CustomTourDTO(int requestID, int customerID, String custName, String farmName, String koiTypeName, String duration, double quotationPrice, int quantity, Date startDate, Date endDate, String status, String managerApprovalStatus, String departureLocation, String image, boolean checked, String detailRejected) {
        this.requestID = requestID;
        this.customerID = customerID;
        this.custName = custName;
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
        this.image = image;
        this.checked = checked;
        this.detailRejected = detailRejected;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDetailRejected() {
        return detailRejected;
    }

    public void setDetailRejected(String detailRejected) {
        this.detailRejected = detailRejected;
    }
    
    
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public double getQuotationPrice() {
        return quotationPrice;
    }

    public void setQuotationPrice(double quotationPrice) {
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
        return "CustomTourDTO{" + "requestID=" + requestID + ", customerID=" + customerID + ", custName=" + custName + ", farmName=" + farmName + ", koiTypeName=" + koiTypeName + ", duration=" + duration + ", quotationPrice=" + quotationPrice + ", quantity=" + quantity + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", managerApprovalStatus=" + managerApprovalStatus + ", departureLocation=" + departureLocation + ", image=" + image + ", checked=" + checked + ", detailRejected=" + detailRejected + '}';
    }

}
