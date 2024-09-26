/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import koikd.customer.CustomerDTO;
import koikd.farm.FarmDAO;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author Minhngo
 */
public class KoiOrderDAO {

    /**
     * getKoiOrderList 
     * @param nameCustomer
     * @return
     * @throws SQLException 
     */
    public ArrayList<KoiOrderDTO> getKoiOrderList(String nameCustomer) throws SQLException {
        ArrayList<KoiOrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT \n"
                        + "    O.KoiOrderID, \n"
                        + "    C.CustomerID,\n"
                        + "    O.DeliveryDate, \n"
                        + "    O.Status\n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O\n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID";
                if (nameCustomer != null && !nameCustomer.isEmpty()) {
                    sql += " WHERE c.LastName + ' ' + c.FirstName LIKE ?";
                }
                pst = conn.prepareStatement(sql);
                if (nameCustomer != null && !nameCustomer.isEmpty()) {
                    pst.setString(1, "%" + nameCustomer + "%");
                }

                rs = pst.executeQuery();
                while (rs.next()) {
                    int KoiOrderID = rs.getInt("KoiOrderID");
                    int customerId = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    KoiOrderDTO dao = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status);
                    list.add(dao);
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
        return list;
    }

    /**
     * getCustomerNameById
     * @param customerId
     * @return
     * @throws SQLException 
     */
    public String getCustomerNameById(int customerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String customerName = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FirstName, LastName FROM [dbo].[CUSTOMER] WHERE CustomerID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerId);

                rs = pst.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    customerName = lastName + " " + firstName; // Combine first and last name
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
        return customerName;
    }

    /**
     * getKoiOrderDetail
     * @param orderKoiId
     * @return
     * @throws SQLException 
     */
    public KoiOrderDetailDTO getKoiOrderDetail(int orderKoiId) throws SQLException {
        KoiOrderDetailDTO detail = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT \n"
                        + "    OD.KoiOrderDetailID, \n"
                        + "    OD.KoiOrderID, \n"
                        + "    OD.KoiID, \n"
                        + "    OD.FarmID, \n"
                        + "    OD.Quantity, \n"
                        + "    OD.UnitPrice, \n"
                        + "    OD.TotalPrice, \n"
                        + "    O.DeliveryDate, \n"
                        + "    O.Status \n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDERDETAIL] OD \n"
                        + "INNER JOIN \n"
                        + "    [dbo].[KOIORDER] O ON OD.KoiOrderID = O.KoiOrderID \n"
                        + "WHERE OD.KoiOrderID = ?";

                pst = conn.prepareStatement(sql);
                pst.setInt(1, orderKoiId);

                rs = pst.executeQuery();
                if (rs.next()) { // Fetch the first result
                    int koiOrderDetailID = rs.getInt("KoiOrderDetailID");
                    int koiOrderID = rs.getInt("KoiOrderID");
                    int koiID = rs.getInt("KoiID");
                    int farmID = rs.getInt("FarmID");
                    int quantity = rs.getInt("Quantity");
                    double unitPrice = rs.getDouble("UnitPrice");
                    double totalPrice = rs.getDouble("TotalPrice");
                    detail = new KoiOrderDetailDTO(koiOrderDetailID, koiOrderID, koiID, farmID, quantity, unitPrice, totalPrice);
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
        return detail;
    }

    /**
     * getFarmName
     * @param farmId
     * @return
     * @throws SQLException 
     */
    public FarmDTO getFarmName(int farmId) throws SQLException {
        FarmDTO detail = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [FarmID], [FarmName], [Location], [Description], [Image], [Status]\n"
                        + "FROM [dbo].[FARM]\n"
                        + "WHERE [FarmID] = ?";

                pst = conn.prepareStatement(sql);
                pst.setInt(1, farmId);

                rs = pst.executeQuery();
                if (rs.next()) {
                    int farmID = rs.getInt("FarmID");
                    String farmName = rs.getString("FarmName");
                    String location = rs.getString("Location");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    boolean status = rs.getBoolean("Status");
                    detail = new FarmDTO(farmID, farmName, location, description, image, status);
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
        return detail;
    }

    /**
     * getKoiName
     * @param koiId
     * @return
     * @throws SQLException 
     */
    public KoiDTO getKoiName(int koiId) throws SQLException {
        KoiDTO detail = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [KoiID], [KoiName], [KoiTypeID], [Age], [Length], [Weight], [Price], [Image]\n"
                        + "FROM [dbo].[KOI]\n"
                        + "WHERE [KoiID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, koiId);

                rs = pst.executeQuery();
                if (rs.next()) {
                    int KoiID = rs.getInt("KoiID");
                    String koiName = rs.getString("KoiName");
                    int koiTypeID = rs.getInt("KoiTypeID");
                    int age = rs.getInt("Age");
                    int length = rs.getInt("Length");
                    int weight = rs.getInt("Weight");
                    int price = rs.getInt("Price");
                    String image = rs.getString("Image");
                    detail = new KoiDTO(KoiID, koiName, koiTypeID, age, length, weight, price, image);
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
        return detail;
    }
}
