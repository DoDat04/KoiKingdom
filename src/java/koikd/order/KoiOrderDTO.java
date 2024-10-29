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
    private Date deliveryDate;
    private boolean status;
    private Date estimatedDelivery;

    private String type;

    private int deliveryBy;

    public KoiOrderDTO() {
    }

    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery, String type) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
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
