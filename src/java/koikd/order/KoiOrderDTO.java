/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.order;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Minhngo
 */
public class KoiOrderDTO implements Serializable {

    private int koiOrderID;
    private int customerID;
    private String fullName;
    private Date deliveryDate;
    private boolean status;
    private Date estimatedDelivery;
    private String type;
    private int deliveryBy;
    private int createBy;
    private double costShipping;
    private String shippingAddress;
    
    public KoiOrderDTO() {
    }

    public KoiOrderDTO(int koiOrderID, int customerID, String fullName, Date deliveryDate, boolean status, Date estimatedDelivery, String type, int deliveryBy, int createBy, double costShipping, String shippingAddress) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.fullName = fullName;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.deliveryBy = deliveryBy;
        this.createBy = createBy;
        this.costShipping = costShipping;
        this.shippingAddress = shippingAddress;
    }
  
    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery, String type) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
    }

    public KoiOrderDTO(int koiOrderID, String fullName, Date deliveryDate, boolean status, Date estimatedDelivery, String type, int createBy) {
        this.koiOrderID = koiOrderID;
        this.fullName = fullName;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.createBy = createBy;
    }
    
    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery, String type, int deliveryBy) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.deliveryBy = deliveryBy;
    }

    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
    }

    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery, String type, double costShipping, String shippingAddress) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.costShipping = costShipping;
        this.shippingAddress = shippingAddress;
    }

    public double getCostShipping() {
        return costShipping;
    }

    public void setCostShipping(double costShipping) {
        this.costShipping = costShipping;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDeliveryBy() {
        return deliveryBy;
    }

    public void setDeliveryBy(int deliveryBy) {
        this.deliveryBy = deliveryBy;
    }

    public int getKoiOrderID() {
        return koiOrderID;
    }

    public void setKoiOrderID(int koiOrderID) {
        this.koiOrderID = koiOrderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(Date estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    @Override
    public String toString() {
        return "KoiOrderDTO{" + "koiOrderID=" + koiOrderID + ", customerID=" + customerID + ", deliveryDate=" + deliveryDate + ", status=" + status + '}';
    }

}
