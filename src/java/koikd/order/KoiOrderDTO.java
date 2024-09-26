/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.order;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class KoiOrderDTO implements Serializable{
    private int koiOrderID;
    private int customerID;
    private Date deliveryDate;
    private boolean status;

    public KoiOrderDTO() {
    }
    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
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

    @Override
    public String toString() {
        return "KoiOrderDTO{" + "koiOrderID=" + koiOrderID + ", customerID=" + customerID + ", deliveryDate=" + deliveryDate + ", status=" + status + '}';
    }
    
    
}
