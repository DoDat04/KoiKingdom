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
import koikd.farm.FarmDTO;
import koikd.koi.KoiDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author Minhngo
 */
public class KoiOrderDAO {

    /**
     * Get order list by customer's name
     *
     * @param nameCustomer
     * @return
     * @throws SQLException
     */
    public ArrayList<KoiOrderDTO> getKoiOrderListByNameCustomer(String nameCustomer) throws SQLException {
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
                        + "    O.Status,\n"
                        + "    O.EstimatedDelivery \n"
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
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    KoiOrderDTO dao = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status, estimatedDelivery);
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
     *  Get Customer By CustomerID
     *
     * @param customerId
     * @return
     * @throws SQLException
     */
    public CustomerDTO getCustomerByCustomerID(int customerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CustomerDTO result = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT CustomerID, Email, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER "
                        + "WHERE CustomerID = ?"; 
                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    String email = rs.getString("Email");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");
                    // Fixed constructor call
                    result = new CustomerDTO(customerID, email, lastName, lastName, firstName, address, accountType, status);
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
        return result;
    }

    /**
     * Get KoiOrderDetail By OrderKoiID
     *
     * @param orderKoiId
     * @return
     * @throws SQLException
     */
    public KoiOrderDetailDTO getKoiOrderDetaiByID(int orderKoiId) throws SQLException {
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
     * Get Farm By Farm ID
     *
     * @param farmId
     * @return
     * @throws SQLException
     */
    public FarmDTO getFarmByFarmID(int farmId) throws SQLException {
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
     * Get Koi Fish By KoiID
     *
     * @param koiId
     * @return
     * @throws SQLException
     */
    public KoiDTO getKoiFishByKoiID(int koiId) throws SQLException {
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

    /**
     * Get KoiOrderList By Id
     * @param customerID
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<KoiOrderDTO> getKoiOrderListByID(int customerID) throws SQLException, ClassNotFoundException {
        ArrayList<KoiOrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT \n"
                        + "    O.KoiOrderID, \n"
                        + "    C.CustomerID, \n"
                        + "    O.DeliveryDate, \n"
                        + "    O.Status, \n"
                        + "    O.EstimatedDelivery \n" // Thêm cột EstimatedDelivery
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O\n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID\n"
                        + "WHERE O.CustomerID = ?";

                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerID);  

                rs = pst.executeQuery();
                while (rs.next()) {
                    int KoiOrderID = rs.getInt("KoiOrderID");
                    int customerId = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");  
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");  
                    // Tạo DTO từ dữ liệu lấy được
                    KoiOrderDTO dao = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status, estimatedDelivery);
                    list.add(dao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
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
     * Get KoiOrderDetailList By ID
     * @param orderKoiId
     * @return
     * @throws SQLException 
     */
    public ArrayList<KoiOrderDetailDTO> getKoiOrderDetaiListById(int orderKoiId) throws SQLException {
        ArrayList<KoiOrderDetailDTO> detail = new ArrayList<>();
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
                while (rs.next()) {
                    int koiOrderDetailID = rs.getInt("KoiOrderDetailID");
                    int koiOrderID = rs.getInt("KoiOrderID");
                    int koiID = rs.getInt("KoiID");
                    int farmID = rs.getInt("FarmID");
                    int quantity = rs.getInt("Quantity");
                    double unitPrice = rs.getDouble("UnitPrice");
                    double totalPrice = rs.getDouble("TotalPrice");
                    KoiOrderDetailDTO orderDetail = new KoiOrderDetailDTO(koiOrderDetailID, koiOrderID, koiID, farmID, quantity, unitPrice, totalPrice);
                    detail.add(orderDetail);
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
     * GetKoiOrderListByOrderID
     * @param koiOrderID
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<KoiOrderDTO> getKoiOrderListByOrderID(int koiOrderID) throws SQLException, ClassNotFoundException {
        ArrayList<KoiOrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT \n"
                        + "    O.KoiOrderID, \n"
                        + "    C.CustomerID, \n"
                        + "    O.DeliveryDate, \n"
                        + "    O.Status, \n"
                        + "    O.EstimatedDelivery \n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O\n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID\n"
                        + "WHERE O.KoiOrderID = ?";

                pst = conn.prepareStatement(sql);
                pst.setInt(1, koiOrderID); 

                rs = pst.executeQuery();
                while (rs.next()) {
                    int KoiOrderID = rs.getInt("KoiOrderID");
                    int customerId = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    KoiOrderDTO dao = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status, estimatedDelivery);
                    list.add(dao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
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

}