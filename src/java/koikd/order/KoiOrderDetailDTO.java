/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.order;

import java.io.Serializable;

/**
 *
 * @author Minhngo
 */
public class KoiOrderDetailDTO implements Serializable {
    private int koiOrderDetailID;
    private int koiOrderID;
    private int koiID;
    private int farmID;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private int koiTypeID;

    public KoiOrderDetailDTO() {
    }   

    public KoiOrderDetailDTO(int koiOrderDetailID, int koiOrderID, int koiID, int farmID, int quantity, double unitPrice, double totalPrice, int koiTypeID) {
        this.koiOrderDetailID = koiOrderDetailID;
        this.koiOrderID = koiOrderID;
        this.koiID = koiID;
        this.farmID = farmID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.koiTypeID = koiTypeID;
    }

    public KoiOrderDetailDTO(double unitPrice, int quantity, double totalPrice) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getKoiTypeID() {
        return koiTypeID;
    }

    public void setKoiTypeID(int koiTypeID) {
        this.koiTypeID = koiTypeID;
    }

    public int getKoiOrderDetailID() {
        return koiOrderDetailID;
    }

    public void setKoiOrderDetailID(int koiOrderDetailID) {
        this.koiOrderDetailID = koiOrderDetailID;
    }

    public int getKoiOrderID() {
        return koiOrderID;
    }

    public void setKoiOrderID(int koiOrderID) {
        this.koiOrderID = koiOrderID;
    }

    public int getKoiID() {
        return koiID;
    }

    public void setKoiID(int koiID) {
        this.koiID = koiID;
    }

    public int getFarmID() {
        return farmID;
    }

    public void setFarmID(int farmID) {
        this.farmID = farmID;
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

    @Override
    public String toString() {
        return "KoiOrderDetailDTO{" + "koiOrderDetailID=" + koiOrderDetailID + ", koiOrderID=" + koiOrderID + ", koiID=" + koiID + ", farmID=" + farmID + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + '}';
    }
    
    
}
