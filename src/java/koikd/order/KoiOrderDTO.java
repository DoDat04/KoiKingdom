/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.order;

import java.io.Serializable;
import java.util.Date;
import koikd.koi.KoiDTO;

/**
 *
 * @author Minhngo
 */
public class KoiOrderDTO implements Serializable {

    private int koiOrderID;
    private int customerID;
    private String fullName;
    private String employeeFullName;
    private Date deliveryDate;
    private boolean status;
    private Date estimatedDelivery;
    private String type;
    private int deliveryBy;
    private int createBy;
    private double costShipping;
    private String shippingAddress;
    private String tempStatus;
    private String payment;
    private KoiDTO koiDTO;
    private KoiOrderDetailDTO koiOrderDetailDTO;

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTempStatus() {
        return tempStatus;
    }

    public void setTempStatus(String tempStatus) {
        this.tempStatus = tempStatus;
    }

    public KoiDTO getKoiDTO() {
        return koiDTO;
    }

    public void setKoiDTO(KoiDTO koiDTO) {
        this.koiDTO = koiDTO;
    }

    public KoiOrderDetailDTO getKoiOrderDetailDTO() {
        return koiOrderDetailDTO;
    }

    public void setKoiOrderDetailDTO(KoiOrderDetailDTO koiOrderDetailDTO) {
        this.koiOrderDetailDTO = koiOrderDetailDTO;
    }

    public KoiOrderDTO(int koiOrderID, int customerID, String shippingAddress, String type, KoiDTO koiDTO, KoiOrderDetailDTO koiOrderDetailDTO, double costShipping) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.shippingAddress = shippingAddress;
        this.type = type;
        this.koiDTO = koiDTO;
        this.koiOrderDetailDTO = koiOrderDetailDTO;
        this.costShipping = costShipping;
    }
    
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
  
    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery, String type, String tempStatus) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.tempStatus = tempStatus;
    }

    public KoiOrderDTO(int koiOrderID,int customerID, String fullName, Date deliveryDate, boolean status, Date estimatedDelivery, String type, int createBy) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.fullName = fullName;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.createBy = createBy;
    }
    
    public KoiOrderDTO(int koiOrderID, int customerID, Date deliveryDate, boolean status, Date estimatedDelivery, String type, int deliveryBy, String tempStatus) {
        this.koiOrderID = koiOrderID;
        this.customerID = customerID;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.type = type;
        this.deliveryBy = deliveryBy;
        this.tempStatus = tempStatus;
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
        return "KoiOrderDTO{" + "koiOrderID=" + koiOrderID + ", customerID=" + customerID + ", fullName=" + fullName + ", deliveryDate=" + deliveryDate + ", status=" + status + ", estimatedDelivery=" + estimatedDelivery + ", type=" + type + ", deliveryBy=" + deliveryBy + ", createBy=" + createBy + ", costShipping=" + costShipping + ", shippingAddress=" + shippingAddress + ", koiDTO=" + koiDTO + ", koiOrderDetailDTO=" + koiOrderDetailDTO + '}';
    }

}
