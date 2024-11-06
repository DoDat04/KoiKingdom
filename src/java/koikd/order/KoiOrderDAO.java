/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import koikd.customer.CustomerDTO;
import java.sql.Timestamp;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDTO;
import koikd.koitype.KoiTypeDAO;
import koikd.koitype.KoiTypeDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author Minhngo, ADMIN LAM
 */
public class KoiOrderDAO implements Serializable {

    public ArrayList<KoiOrderDTO> getKoiOrderListByNameCustomer(String searchData, int index, int employeeId) throws SQLException {
        ArrayList<KoiOrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Base query
                String sql = "SELECT \n"
                        + "    O.KoiOrderID, \n"
                        + "    C.CustomerID, \n"
                        + "    C.PhoneNumber, \n"
                        + "    O.DeliveryDate, \n"
                        + "    O.Status, \n"
                        + "    O.EstimatedDelivery, \n"
                        + "    O.DeliveryBy, \n"
                        + "    O.Type \n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O \n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID \n";

                // Track if a condition has been added
                boolean hasCondition = false;

                if (employeeId != 0) {
                    sql += "WHERE O.DeliveryBy = ? \n";
                    hasCondition = true;
                }

                if (searchData != null && !searchData.isEmpty()) {
                    sql += (hasCondition ? "AND " : "WHERE ") + "(C.LastName + ' ' + C.FirstName LIKE ?) \n";
                }

                sql += "ORDER BY O.KoiOrderID ASC \n"
                        + "OFFSET ? ROWS \n"
                        + "FETCH NEXT 5 ROWS ONLY;";

                pst = conn.prepareStatement(sql);

                int paramIndex = 1;

                // If employeeId is provided, set it in the prepared statement
                if (employeeId != 0) {
                    pst.setInt(paramIndex++, employeeId);
                }

                // If searchData is provided, set it in the prepared statement
                if (searchData != null && !searchData.isEmpty()) {
                    pst.setString(paramIndex++, "%" + searchData + "%");
                }

                // Set index for pagination
                pst.setInt(paramIndex++, (index - 1) * 5); // Set the pagination index

                // Execute the query
                rs = pst.executeQuery();
                while (rs.next()) {
                    int koiOrderID = rs.getInt("KoiOrderID");
                    int customerID = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    String type = rs.getString("Type");
                    int deliveryBy = rs.getInt("DeliveryBy");

                    // Create the DTO and add to the list
                    KoiOrderDTO orderDTO = new KoiOrderDTO(koiOrderID, customerID, deliveryDate, status, estimatedDelivery, type, deliveryBy);
                    list.add(orderDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log specific error messages
            throw e; // Rethrow exception for handling
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources to avoid memory leaks
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

        return list; // Return the list outside of the finally block
    }

    /**
     * Get Customer By CustomerID
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
                String sql = "SELECT CustomerID, Email, LastName, FirstName, Address, AccountType, Status, PhoneNumber "
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
                    String phoneNumber = rs.getString("PhoneNumber");
                    boolean status = rs.getBoolean("Status");
                    // Fixed constructor call
                    result = new CustomerDTO(customerID, email, lastName, lastName, firstName, address, accountType, phoneNumber ,status);
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
                        + "    OD.KoiTypeID, \n"
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
                    int koiTypeId = rs.getInt("KoiTypeID");
                    detail = new KoiOrderDetailDTO(koiOrderDetailID, koiOrderID, koiID, farmID, quantity, unitPrice,
                            totalPrice, koiTypeId);
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
                    double length = rs.getDouble("Length");
                    double weight = rs.getDouble("Weight");
                    double price = rs.getDouble("Price");
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
     *
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
                        + "    O.EstimatedDelivery, \n" // Thêm cột EstimatedDelivery
                        + "    O.Type \n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O\n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID\n"
                        + "WHERE O.CustomerID = ? "
                        + "ORDER BY O.DeliveryDate";

                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerID);

                rs = pst.executeQuery();
                while (rs.next()) {
                    int KoiOrderID = rs.getInt("KoiOrderID");
                    int customerId = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    String type = rs.getString("Type");
                    // Tạo DTO từ dữ liệu lấy được
                    KoiOrderDTO dao = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status, estimatedDelivery,
                            type);
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
     *
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
                        + "    OD.KoiTypeID, \n"
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
                    int koiTypeId = rs.getInt("KoiTypeID");
                    KoiOrderDetailDTO orderDetail = new KoiOrderDetailDTO(koiOrderDetailID, koiOrderID, koiID, farmID,
                            quantity, unitPrice, totalPrice, koiTypeId);
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
     *
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
                        + "    O.EstimatedDelivery, \n"
                        + "    O.Type, \n"
                        + "    O.CostShipping, \n"
                        + "     O.ShippingAddress\n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O\n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID\n"
                        + "WHERE O.KoiOrderID = ? "
                        + "ORDER BY O.DeliveryDate ASC";

                pst = conn.prepareStatement(sql);

                pst.setInt(1, koiOrderID);

                rs = pst.executeQuery();
                while (rs.next()) {

                    int KoiOrderID = rs.getInt("KoiOrderID");
                    int customerId = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");

                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    String type = rs.getString("Type");
                    double costShipping = rs.getDouble("CostShipping");
                    String shippingAddress = rs.getString("ShippingAddress");

                    KoiOrderDTO dao = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status, estimatedDelivery,
                            type, costShipping, shippingAddress);
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
     *
     * @param koiOrderDTO
     * @return create koi order form
     * @throws java.sql.SQLException
     */
    public int createKoiOrder(KoiOrderDTO koiOrderDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int koiOrderID = -1; // -1 để chỉ ra rằng chưa có giá trị KoiOrderID được lấy
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[KOIORDER]([CustomerID], [DeliveryDate], [Status], [EstimatedDelivery], [Type], [CreatedBy]) "
                        + "VALUES(?, ?, ?, NULL, ?, ?)";
                pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // Lấy giá trị khóa tự động
                // sinh
                pst.setInt(1, koiOrderDTO.getCustomerID());
                pst.setTimestamp(2, new java.sql.Timestamp(koiOrderDTO.getDeliveryDate().getTime()));
                pst.setBoolean(3, koiOrderDTO.isStatus());
                pst.setString(4, koiOrderDTO.getType());
                pst.setInt(5, koiOrderDTO.getCreateBy());

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    // Lấy KoiOrderID tự động sinh
                    rs = pst.getGeneratedKeys();

                    if (rs.next()) {
                        koiOrderID = rs.getInt(1); // Lấy giá trị KoiOrderID từ kết quả
                    }
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
        return koiOrderID; // Trả về KoiOrderID hoặc -1 nếu có lỗi
    }

    /**
     *
     * @param koiOrderDetail O //
     * @return create koi order detail
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    /**
     *
     * @param koiOrderDetailDTO
     * @return create koi order detail
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean createKoiOrderDetail(KoiOrderDetailDTO koiOrderDetailDTO) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[KOIORDERDETAIL]([KoiOrderID], [KoiID], [FarmID], [Quantity], [UnitPrice], [TotalPrice], [KoiTypeID] ) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, koiOrderDetailDTO.getKoiOrderID());
                pst.setInt(2, koiOrderDetailDTO.getKoiID());
                pst.setInt(3, koiOrderDetailDTO.getFarmID());
                pst.setInt(4, koiOrderDetailDTO.getQuantity());
                pst.setDouble(5, koiOrderDetailDTO.getUnitPrice());
                pst.setDouble(6, koiOrderDetailDTO.getTotalPrice());
                pst.setInt(7, koiOrderDetailDTO.getKoiTypeID());
                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
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
     * Get Number Page
     *
     * @param nameCustomer
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int getNumberPage(String nameCustomer, int deliveryBy) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int countPage = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) FROM KoiOrder O "
                        + "INNER JOIN Customer C ON O.CustomerID = C.CustomerID ";
                boolean hasCondition = false;

                if (nameCustomer != null && !nameCustomer.isEmpty()) {
                    sql += "WHERE C.LastName + ' ' + C.FirstName LIKE ?";
                    hasCondition = true;
                }
                if (deliveryBy != 0) {
                    if (hasCondition) {
                        sql += " AND "; // If a condition already exists
                    } else {
                        sql += "WHERE "; // If no conditions exist
                    }
                    sql += "O.DeliveryBy = ?"; // Use = instead of LIKE for ID
                }

                pst = conn.prepareStatement(sql);
                int paramIndex = 1; // Parameter index

                // Set parameters in the prepared statement
                if (nameCustomer != null && !nameCustomer.isEmpty()) {
                    pst.setString(paramIndex++, "%" + nameCustomer + "%");
                }
                if (deliveryBy != 0) {
                    pst.setInt(paramIndex++, deliveryBy); // Use paramIndex++
                }

                rs = pst.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1);
                    countPage = total / 5;

                    if (total % 5 != 0) {
                        countPage++;
                    }
                }
            }
        } finally {
            // Close resources to avoid memory leaks
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
        return countPage;
    }

    /**
     * Update Status Order
     *
     * @param koiOrderID
     * @param status
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public KoiOrderDTO updateStatusOrder(int koiOrderID, boolean status) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        KoiOrderDTO updatedOrder = new KoiOrderDTO();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[KOIORDER] SET [Status] = ? WHERE [KoiOrderID] = ?;";
                pst = conn.prepareStatement(sql);
                pst.setBoolean(1, status);
                pst.setInt(2, koiOrderID);

                int affectedRows = pst.executeUpdate();

                if (affectedRows > 0) {
                    updatedOrder.setStatus(status);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return updatedOrder;
    }

    /**
     * getListKoi for consulting staff when they insert koiOrder
     *
     * @param searchKoi
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<KoiDTO> getListKoi(String searchKoi) throws SQLException {
        ArrayList<KoiDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [KoiID], [KoiName], [KoiTypeID], [Age], [Length], [Weight], [Price], [Image] "
                        + " FROM [dbo].[KOI]";
                if (searchKoi != null && !searchKoi.isEmpty()) {
                    sql += "WHERE [KoiName] LIKE ?";
                }
                pst = conn.prepareStatement(sql);
                if (searchKoi != null && !searchKoi.isEmpty()) {
                    pst.setString(1, "%" + searchKoi + "%");
                }
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int koiID = rs.getInt("KoiID");
                        String koiName = rs.getString("KoiName");
                        int koiTypeID = rs.getInt("KoiTypeID");
                        int age = rs.getInt("Age");
                        double length = rs.getDouble("Length");
                        double weight = rs.getDouble("Weight");
                        double price = rs.getDouble("Price");
                        String image = rs.getString("Image");
                        KoiDTO dto = new KoiDTO(koiID, koiName, koiTypeID, age, length, weight, price, image);
                        list.add(dto);
                    }
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

    public int countKoiOrder(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int orderCount = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql;
                if (startDate != null && endDate != null) {
                    sql = "SELECT COUNT([KoiOrderID]) FROM [dbo].[KOIORDER]"
                            + "WHERE CAST(DeliveryDate AS DATE) BETWEEN ? AND ? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    sql = "SELECT COUNT([KoiOrderID]) FROM [dbo].[KOIORDER]";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    orderCount = rs.getInt(1); // ta phải lấy ở cột đầu tiên của result set mặc dù nó chỉ có đúng 1 cột
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderCount;
    }

    public double countRevenue(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        double revenueCount = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql;
                if (startDate != null && endDate != null) {
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(tbd.TotalPrice)\n"
                            + "     FROM TOURBOOKINGDETAIL tbd\n"
                            + "     INNER JOIN BOOKING b ON tbd.bookingID = b.BookingID\n"
                            + "     WHERE CAST(b.BookingDate AS DATE) BETWEEN ? AND ?), 0)) \n"
                            + "    + \n"
                            + "    (COALESCE((SELECT SUM(k.TotalPrice * 0.10) \n"
                            + "     FROM KOIORDERDETAIL k \n"
                            + "     INNER JOIN KOIORDER ko ON k.KoiOrderID = ko.KoiOrderID \n"
                            + "     WHERE CAST(ko.DeliveryDate AS DATE) BETWEEN ? AND ?), 0))";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                    pst.setString(3, startDate);
                    pst.setString(4, endDate);
                } else {
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(tbd.TotalPrice)\n"
                            + "     FROM TOURBOOKINGDETAIL tbd\n"
                            + "     INNER JOIN BOOKING b ON tbd.bookingID = b.BookingID), 0)) \n"
                            + "    + \n"
                            + "    (COALESCE((SELECT SUM(k.TotalPrice * 0.10) \n"
                            + "     FROM KOIORDERDETAIL k \n"
                            + "     INNER JOIN KOIORDER ko ON k.KoiOrderID = ko.KoiOrderID), 0))";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    revenueCount = rs.getDouble(1); // ta phải lấy ở cột đầu tiên của result set mặc dù nó chỉ có đúng 1
                    // cột
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return revenueCount;
    }

    public double revenueFromAvailableTour(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        double revenueCount = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql;
                if (startDate != null && endDate != null) {
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(tbd.TotalPrice)\n"
                            + "     FROM TOURBOOKINGDETAIL tbd\n"
                            // 
                            + "     INNER JOIN BOOKING b ON tbd.bookingID = b.BookingID\n"
                            + "     WHERE b.TourType = 'Available' AND CAST(b.BookingDate AS DATE) BETWEEN ? AND ?), 0)) ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(tbd.TotalPrice)\n"
                            + "     FROM TOURBOOKINGDETAIL tbd\n"
                            // 
                            + "     INNER JOIN BOOKING b ON tbd.bookingID = b.BookingID\n"
                            + "     WHERE b.TourType = 'Available'), 0)) ";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    revenueCount = rs.getDouble(1); // ta phải lấy ở cột đầu tiên của result set mặc dù nó chỉ có đúng 1
                    // 
                    // cột
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return revenueCount;
    }

    public double revenueFromCustomTour(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        double revenueCount = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql;
                if (startDate != null && endDate != null) {
                    // 
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(tbd.TotalPrice)\n"
                            + "     FROM TOURBOOKINGDETAIL tbd\n"
                            + "     INNER JOIN BOOKING b ON tbd.bookingID = b.BookingID\n"
                            + "     WHERE b.TourType = 'Custom' AND CAST(b.BookingDate AS DATE) BETWEEN ? AND ?), 0)) ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    sql = "SELECT \n"
                            // 
                            + "    (COALESCE((SELECT SUM(tbd.TotalPrice)\n"
                            + "     FROM TOURBOOKINGDETAIL tbd\n"
                            + "     INNER JOIN BOOKING b ON tbd.bookingID = b.BookingID\n"
                            + "     WHERE b.TourType = 'Custom'), 0)) ";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    // 
                    revenueCount = rs.getDouble(1); // ta phải lấy ở cột đầu tiên của result set mặc dù nó chỉ có đúng 1
                    // cột
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return revenueCount;
    }

    public double commission(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        double revenueCount = 0;

        try {
            conn = DBUtils.getConnection();
            // 
            if (conn != null) {
                String sql;
                if (startDate != null && endDate != null) {
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(k.TotalPrice * 0.10) \n"
                            + "     FROM KOIORDERDETAIL k \n"
                            + "     INNER JOIN KOIORDER ko ON k.KoiOrderID = ko.KoiOrderID \n"
                            + "     WHERE CAST(ko.DeliveryDate AS DATE) BETWEEN ? AND ?), 0))";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                    // 

                } else {
                    sql = "SELECT \n"
                            + "    (COALESCE((SELECT SUM(k.TotalPrice * 0.10) \n"
                            + "     FROM KOIORDERDETAIL k \n"
                            + "     INNER JOIN KOIORDER ko ON k.KoiOrderID = ko.KoiOrderID), 0))";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                // 
                if (rs.next()) {
                    revenueCount = rs.getDouble(1); // ta phải lấy ở cột đầu tiên của result set mặc dù nó chỉ có đúng 1
                    // cột
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return revenueCount;
    }

    public List<KoiDTO> getKoiList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiDTO> koiList = new ArrayList<>();
        // 

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT KoiID, KoiName, KoiTypeID, Age, Length, Weight, Price, Image "
                        + "FROM KOI";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiID = rs.getInt("KoiID");
                    String koiName = rs.getString("KoiName");
                    int koiTypeID = rs.getInt("KoiTypeID");
                    // 
                    int age = rs.getInt("Age");
                    double length = rs.getDouble("Length");
                    double weight = rs.getDouble("Weight");
                    double price = rs.getDouble("Price");
                    String image = rs.getString("Image");

                    KoiDTO dto = new KoiDTO(koiID, koiName, koiTypeID, age, length, weight, price, image);
                    koiList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                // 
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return koiList;
    }

    public double getKoiUnitPrice(int koiID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        double unitPrice = 0;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT Price FROM KOI WHERE KoiID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, koiID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    unitPrice = rs.getDouble("Price");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return unitPrice;
    }

    /**
     * Filter Koi Order List
     *
     * @param customerID
     * @param dateDelivery
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<KoiOrderDTO> filterKoiOrderList(int customerID, String dateDelivery) throws ClassNotFoundException, SQLException {
        ArrayList<KoiOrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Basic SQL query without date filter
                String sql = "SELECT \n"
                        + "    O.KoiOrderID, \n"
                        + "    C.CustomerID, \n"
                        + "    O.DeliveryDate, \n"
                        + "    O.Status, \n"
                        + "    O.EstimatedDelivery, \n"
                        + "    O.Type \n"
                        + "FROM \n"
                        + "    [dbo].[KOIORDER] O\n"
                        + "INNER JOIN \n"
                        + "    [dbo].[CUSTOMER] C ON O.CustomerID = C.CustomerID\n"
                        + "WHERE O.CustomerID = ? ";

                // Append date filter only if dateDelivery is provided
                if (dateDelivery != null && !dateDelivery.isEmpty()) {
                    sql += " AND CAST(O.DeliveryDate AS DATE) = ? "; // Filter by date only
                }

                sql += "ORDER BY O.DeliveryDate DESC";

                // Prepare the statement
                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerID);

                // Set the dateDelivery parameter only if it is provided
                if (dateDelivery != null && !dateDelivery.isEmpty()) {
                    pst.setString(2, dateDelivery); // Set the second parameter for date
                }

                // Execute the query
                rs = pst.executeQuery();
                while (rs.next()) {
                    int KoiOrderID = rs.getInt("KoiOrderID");
                    int customerId = rs.getInt("CustomerID");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    String type = rs.getString("Type");

                    // Create DTO and add to the list
                    KoiOrderDTO order = new KoiOrderDTO(KoiOrderID, customerId, deliveryDate, status, estimatedDelivery, type);
                    list.add(order);
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
     *
     *
     * @param employeeID
     * @param koiOrderID
     * @return
     * @throws ClassNotFoundException
     */
    public boolean assignedToDelivery(int employeeID, int koiOrderID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean isUpdated = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Câu lệnh SQL để cập nhật employeeID vào bảng KOIORDER với điều kiện

                // koiOrderID tương ứng
                String sql = "UPDATE [dbo].[KOIORDER] SET DeliveryBy = ? WHERE koiOrderID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, employeeID);
                pst.setInt(2, koiOrderID);

                int rowsUpdated = pst.executeUpdate();
                isUpdated = rowsUpdated > 0; // Trả về true nếu cập nhật thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {

                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
                // 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }

    public List<KoiOrderDTO> getKoiOrderForConsulting(int consultingID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiOrderDTO> koiOrderList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ko.KoiOrderID, c.FirstName + ' ' + c.LastName AS FullName, "
                        + "ko.DeliveryDate, ko.EstimatedDelivery, ko.Type, ko.Status, "
                        + "ko.CreatedBy "
                        + "FROM KOIORDER ko "
                        + "INNER JOIN CUSTOMER c ON ko.CustomerID = c.CustomerID "
                        + "WHERE ko.CreatedBy = ? AND ko.Type = 'Offline' ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, consultingID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiOrderID = rs.getInt("KoiOrderID");
                    String fullName = rs.getString("FullName");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    String type = rs.getString("Type");
                    int createBy = rs.getInt("CreatedBy");

                    KoiOrderDTO koiOrder = new KoiOrderDTO(koiOrderID, fullName, deliveryDate, status, estimatedDelivery, type, createBy);
                    koiOrderList.add(koiOrder);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return koiOrderList;
    }

    public List<KoiOrderDTO> getKoiBookingForConsulting() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiOrderDTO> koiOrderList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ko.KoiOrderID, c.FirstName + ' ' + c.LastName AS FullName, "
                        + "ko.DeliveryDate, ko.EstimatedDelivery, ko.Type, ko.Status, "
                        + "ko.CreatedBy "
                        + "FROM KOIORDER ko "
                        + "INNER JOIN CUSTOMER c ON ko.CustomerID = c.CustomerID "
                        + "WHERE ko.Type = 'Online' ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiOrderID = rs.getInt("KoiOrderID");
                    String fullName = rs.getString("FullName");
                    Date deliveryDate = rs.getDate("DeliveryDate");
                    boolean status = rs.getBoolean("Status");
                    Date estimatedDelivery = rs.getDate("EstimatedDelivery");
                    String type = rs.getString("Type");
                    int createBy = rs.getInt("CreatedBy");

                    KoiOrderDTO koiOrder = new KoiOrderDTO(koiOrderID, fullName, deliveryDate, status, estimatedDelivery, type, createBy);
                    koiOrderList.add(koiOrder);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return koiOrderList;
    }

    public void updateEstimatedDate(int koiOrderID, Timestamp estimatedDelivery) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE KOIORDER SET EstimatedDelivery = ? WHERE KoiOrderID = ?";
            stm = con.prepareStatement(sql);
            stm.setTimestamp(1, estimatedDelivery);
            stm.setInt(2, koiOrderID);
            stm.executeUpdate();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}

// 
// 
