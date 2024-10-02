/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tourbookingdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import koikd.utils.DBUtils;

/**
 *
 * @author Do Dat
 */
public class TourBookingDetailDAO implements Serializable{
    public boolean addTourBookingDetail(TourBookingDetailDTO tourBookingDetail) throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO TOURBOOKINGDETAIL (CustomerID, TourID, Quantity, UnitPrice, TotalPrice, Status) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, tourBookingDetail.getCustomerID());
                stm.setInt(2, tourBookingDetail.getTourID());
                stm.setInt(3, tourBookingDetail.getQuantity());
                stm.setDouble(4, tourBookingDetail.getUnitPrice());
                stm.setDouble(5, tourBookingDetail.getTotalPrice());
                stm.setString(6, tourBookingDetail.getStatus());
                
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    result = true;
                }
            }           
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }            
        }        
        return result;
    }
}
