/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tourbookingdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import koikd.utils.DBUtils;

/**
 *
 * @author Do Dat
 */
public class TourBookingDetailDAO implements Serializable {

    public boolean addTourBookingDetail(TourBookingDetailDTO tourBookingDetail) throws SQLException, ClassNotFoundException {
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

    public TourBookingDetailDTO getTourBookingDetailByCustomerID(int custID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        TourBookingDetailDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [TourBookingDetail], [CustomerID], [TourID], [Quantity], [UnitPrice], [TotalPrice], [Status]\n"
                        + "FROM [dbo].[TOURBOOKINGDETAIL]\n"
                        + "WHERE [CustomerID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, custID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int tourBookingDetail = rs.getInt("tourBookingDetail");
                    int customerID = rs.getInt("customerID");
                    int tourID = rs.getInt("tourID");
                    int quantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("unitPrice");
                    double totalPrice = rs.getDouble("totalPrice");
                    String status = rs.getString("status");
                    dto = new TourBookingDetailDTO(tourBookingDetail, customerID, tourID, quantity, unitPrice, totalPrice, status);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }
    
//    public static void main(String[] args) throws SQLException {
//        int id = 12;
//        TourBookingDetailDAO dao = new TourBookingDetailDAO();
//        TourBookingDetailDTO dto = dao.getTourBookingDetailByCustomerID(id);
//        if(dto!=null){
//            System.out.println(dto);
//        }
//    }
}
