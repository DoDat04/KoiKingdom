package koikd.customtour;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import koikd.utils.DBUtils;

/**
 *
 * @author ADMIN LAM
 */
public class CustomTourDAO implements Serializable {

    /**
     *
     * @param customTourDTO
     * @return form of custom tour for CUSTOMER.
     * @throws SQLException
     */
    public boolean createCustomTourFromCustomer(CustomTourDTO customTourDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean resultSet = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[CUSTOMTOURREQUEST] "
                        + "([CustomerID], [FullName], [Duration], [StartDate], [EndDate], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], [KoiTypeName], [Quantity], [Image]) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                pst = conn.prepareStatement(sql);

                pst.setInt(1, customTourDTO.getCustomerID());
                pst.setString(2, customTourDTO.getCustName());
                pst.setString(3, customTourDTO.getDuration());
                pst.setTimestamp(4, new java.sql.Timestamp(customTourDTO.getStartDate().getTime()));
                pst.setTimestamp(5, new java.sql.Timestamp(customTourDTO.getEndDate().getTime()));
                pst.setString(6, customTourDTO.getStatus());
                pst.setString(7, customTourDTO.getManagerApprovalStatus());
                pst.setString(8, customTourDTO.getDepartureLocation());
                pst.setString(9, customTourDTO.getFarmName());
                pst.setString(10, customTourDTO.getKoiTypeName());
                pst.setInt(11, customTourDTO.getQuantity());
                pst.setString(12, "img/TourImage/1.jpg");

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    resultSet = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error while creating custom tour: " + e.getMessage());
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultSet;
    }

//    public static void main(String[] args) {
//        CustomTourDTO customTourDTO = new CustomTourDTO();
//        
//        // Thiết lập thông tin cho CustomTourDTO
//        customTourDTO.setDuration("5 Days 4 Nights");
//        
//        Calendar cal = Calendar.getInstance();
//        
//        // Thiết lập ngày bắt đầu
//        cal.set(2024, Calendar.OCTOBER, 15);
//        Date startDate = cal.getTime();
//        customTourDTO.setStartDate(startDate);
//        
//        // Thiết lập ngày kết thúc
//        cal.set(2024, Calendar.OCTOBER, 20);
//        Date endDate = cal.getTime();
//        customTourDTO.setEndDate(endDate);
//        
//        // Thiết lập các thông tin khác
//        customTourDTO.setStatus("Pending");
//        customTourDTO.setManagerApprovalStatus("Pending");
//        customTourDTO.setDepartureLocation("Tokyo, Japan");
//        customTourDTO.setFarmName("Koi Farm A");
//        customTourDTO.setFarmName("Koi Farm B");
//        customTourDTO.setFarmName("Koi Farm C");
//        customTourDTO.setKoiTypeName("Tancho");
//        customTourDTO.setKoiTypeName("Tancho B");
//        customTourDTO.setKoiTypeName("Tancho C");
//        customTourDTO.setQuantity(2); 
//
//        // Tạo đối tượng CustomTourDAO để gọi phương thức tạo tour
//        CustomTourDAO customTourDAO = new CustomTourDAO();
//
//        try {
//            // Gọi phương thức tạo custom tour
//            boolean isCreated = customTourDAO.createCustomTourFromCustomer(customTourDTO);
//
//            // Kiểm tra kết quả
//            if (isCreated) {
//                System.out.println("Custom tour was created successfully!");
//            } else {
//                System.out.println("Failed to create custom tour.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Database error: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
    /**
     *
     * @param fullName
     * @return list of custom tour for SALES.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<CustomTourDTO> getListCustomTour(String fullName) throws SQLException, ClassNotFoundException {
        ArrayList<CustomTourDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [RequestID], [CustomerID], [FullName], [Duration], [StartDate], "
                        + " [EndDate], [QuotationPrice], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], "
                        + " [KoiTypeName], [Quantity], [Image], [DetailRejected],[Checked] FROM [dbo].[CUSTOMTOURREQUEST] ";
                if (fullName != null && !fullName.isEmpty()) {
                    sql += " WHERE [FullName] LIKE ?";
                }
                pst = conn.prepareStatement(sql);

                if (fullName != null && !fullName.isEmpty()) {
                    pst.setString(1, "%" + fullName + "%");
                }

                rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int requestID = rs.getInt("RequestID");
                        int customerID = rs.getInt("CustomerID");
                        String custName = rs.getString("FullName");
                        String farmName = rs.getString("FarmName");
                        String koiTypeName = rs.getString("KoiTypeName");
                        String duration = rs.getString("Duration");
                        int quantity = rs.getInt("Quantity");
                        Date startDate = rs.getDate("StartDate");
                        Date endDate = rs.getDate("EndDate");
                        double quotationPrice = rs.getDouble("QuotationPrice");
                        String status = rs.getString("Status");
                        String managerApprovalStatus = rs.getString("ManagerApprovalStatus");
                        String departureLocation = rs.getString("DepartureLocation");
                        String image = rs.getString("Image");
                        boolean checked = rs.getBoolean("checked");
                        String detailRejected = rs.getString("detailRejected");
                        CustomTourDTO customTourDTO = new CustomTourDTO(requestID, customerID, custName, farmName, koiTypeName, duration, quotationPrice, quantity, startDate, endDate, status, managerApprovalStatus, departureLocation, image, checked, detailRejected);
                        list.add(customTourDTO);
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

    public List<CustomTourDTO> getListCustomTour() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CustomTourDTO> listCustomTour = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [RequestID], [CustomerID], [FullName], [Duration], [StartDate], "
                        + "[EndDate], [QuotationPrice], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], "
                        + "[KoiTypeName], [Quantity], [Image], [DetailRejected], [Checked] FROM [dbo].[CUSTOMTOURREQUEST] ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int requestID = rs.getInt("RequestID");
                        int customerID = rs.getInt("CustomerID");
                        String custName = rs.getString("FullName");
                        String farmName = rs.getString("FarmName");
                        String koiTypeName = rs.getString("KoiTypeName");
                        String duration = rs.getString("Duration");
                        int quantity = rs.getInt("Quantity");
                        Date startDate = rs.getDate("StartDate");
                        Date endDate = rs.getDate("EndDate");
                        double quotationPrice = rs.getDouble("QuotationPrice");
                        String status = rs.getString("Status");
                        String managerApprovalStatus = rs.getString("ManagerApprovalStatus");
                        String departureLocation = rs.getString("DepartureLocation");
                        String image = rs.getString("Image");
                        boolean checked = rs.getBoolean("checked");
                        String detailRejected = rs.getString("detailRejected");
                        CustomTourDTO customTourDTO = new CustomTourDTO(requestID, customerID, custName, farmName, koiTypeName, duration, quotationPrice, quantity, startDate, endDate, status, managerApprovalStatus, departureLocation, image, checked, detailRejected);
                        listCustomTour.add(customTourDTO);
                    }
                }
            }
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

        return listCustomTour;
    }

    public List<CustomTourDTO> getListCustomTourForManager() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CustomTourDTO> listCustomTour = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [RequestID], [CustomerID], [FullName], [Duration], [StartDate], "
                        + "[EndDate], [QuotationPrice], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], "
                        + "[KoiTypeName], [Quantity], [Image] "
                        + "FROM [dbo].[CUSTOMTOURREQUEST] "
                        + "WHERE Checked = 1";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int requestID = rs.getInt("RequestID");
                        int customerID = rs.getInt("CustomerID");
                        String custName = rs.getString("FullName");
                        String farmName = rs.getString("FarmName");
                        String koiTypeName = rs.getString("KoiTypeName");
                        String duration = rs.getString("Duration");
                        int quantity = rs.getInt("Quantity");
                        Date startDate = rs.getDate("StartDate");
                        Date endDate = rs.getDate("EndDate");
                        double quotationPrice = rs.getDouble("QuotationPrice");
                        String status = rs.getString("Status");
                        String managerApprovalStatus = rs.getString("ManagerApprovalStatus");
                        String departureLocation = rs.getString("DepartureLocation");
                        String image = rs.getString("Image");

                        CustomTourDTO customTourDTO = new CustomTourDTO(requestID, customerID, custName, farmName, koiTypeName, duration, quotationPrice, quantity, startDate, endDate, status, managerApprovalStatus, departureLocation, image);
                        listCustomTour.add(customTourDTO);
                    }
                }
            }
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

        return listCustomTour;
    }

    public List<CustomTourDTO> getListCustomTourForConsulting() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CustomTourDTO> listCustomTour = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [RequestID], [CustomerID], [FullName], [Duration], [StartDate], "
                        + "[EndDate], [QuotationPrice], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], "
                        + "[KoiTypeName], [Quantity], [Image] "
                        + "FROM [dbo].[CUSTOMTOURREQUEST] "
                        + "WHERE Status = 'Approved'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int requestID = rs.getInt("RequestID");
                        int customerID = rs.getInt("CustomerID");
                        String custName = rs.getString("FullName");
                        String farmName = rs.getString("FarmName");
                        String koiTypeName = rs.getString("KoiTypeName");
                        String duration = rs.getString("Duration");
                        int quantity = rs.getInt("Quantity");
                        Date startDate = rs.getDate("StartDate");
                        Date endDate = rs.getDate("EndDate");
                        double quotationPrice = rs.getDouble("QuotationPrice");
                        String status = rs.getString("Status");
                        String managerApprovalStatus = rs.getString("ManagerApprovalStatus");
                        String departureLocation = rs.getString("DepartureLocation");
                        String image = rs.getString("Image");

                        CustomTourDTO customTourDTO = new CustomTourDTO(requestID, customerID, custName, farmName, koiTypeName, duration, quotationPrice, quantity, startDate, endDate, status, managerApprovalStatus, departureLocation, image);
                        listCustomTour.add(customTourDTO);
                    }
                }
            }
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

        return listCustomTour;
    }

    public boolean updateManagerApprovalStatus(int requestID, String status) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[CUSTOMTOURREQUEST] SET ManagerApprovalStatus = ? WHERE RequestID = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, status);
                pst.setInt(2, requestID);

                result = pst.executeUpdate() > 0;
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

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        String fullName = "";
//        CustomTourDAO dao = new CustomTourDAO();
//        ArrayList<CustomTourDTO> list = dao.getListCustomTour(fullName);
//        for (CustomTourDTO customTourDTO : list) {
//            if (customTourDTO != null) {
//                System.out.println(customTourDTO);
//            }
//        }
//    }
    /**
     *
     * @param quoPrice
     * @param reqID
     * @return update quotation price from requestID.
     * @throws SQLException
     */
    public boolean updatePriceCustomTourBySales(double quoPrice, int reqID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean rs = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[CUSTOMTOURREQUEST] "
                        + " SET [QuotationPrice] = ? "
                        + " WHERE [RequestID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setDouble(1, quoPrice);
                pst.setInt(2, reqID);
                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    rs = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return rs;
    }

//    public static void main(String[] args) throws SQLException {
//        int reqID = 1;
//        double quoPrice = 150;
//        CustomTourDAO dao = new CustomTourDAO();
//        boolean dto = dao.updatePriceCustomTourBySales(quoPrice, reqID);
//        if (dto) {
//            System.out.println("Update successfully!");
//        } else {
//            System.out.println("Fail.");
//        }
//    }
    /**
     *
     * @param statusSales
     * @param reqID
     * @return status for requestID.
     * @throws SQLException
     */
    public boolean updateStatusCustomTourBySales(String statusSales, int reqID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean rs = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[CUSTOMTOURREQUEST] "
                        + " SET [Status] = ? "
                        + " WHERE [RequestID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, statusSales);
                pst.setInt(2, reqID);
                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    rs = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return rs;
    }

//    public static void main(String[] args) throws SQLException {
//        int reqID = 1;
//        String status = "Approved";
//        CustomTourDAO dao = new CustomTourDAO();
//        boolean dto = dao.updateStatusCustomTourBySales(status, reqID);
//        if(dto){
//            System.out.println("Updated successfully.");
//        } else{
//            System.out.println("Fail.");
//        }
//    }
    /**
     *
     * @param reqID
     * @return send data from sales to manager.
     * @throws SQLException
     */
    public boolean sendDetailCustomTourFromSalesToManager(int reqID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean rs = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[CUSTOMTOURREQUEST] "
                        + " SET [Checked] = 1 "
                        + " WHERE [RequestID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, reqID);
                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    rs = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return rs;
    }

    public boolean updateQuotationAndRejectionDetails(int requestID, double newPrice, String rejectReason) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean isUpdated = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[CUSTOMTOURREQUEST] "
                        + "SET [QuotationPrice] = ?, [ManagerApprovalStatus] = 'Rejected', [DetailRejected] = ? "
                        + "WHERE [RequestID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setDouble(1, newPrice);
                pst.setString(2, rejectReason);
                pst.setInt(3, requestID);
                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    isUpdated = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isUpdated;
    }

    /**
     * GetListCustomTourByID
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<CustomTourDTO> getListCustomTourByID(int id) throws SQLException, ClassNotFoundException {
        ArrayList<CustomTourDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [RequestID], [CustomerID], [FullName], [Duration], [StartDate],\n"
                        + "[EndDate], [QuotationPrice], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], \n"
                        + " [KoiTypeName], [Quantity], [Image], [DetailRejected],[Checked] \n"
                        + " FROM [dbo].[CUSTOMTOURREQUEST] \n"
                        + " WHERE [CustomerID] = ? AND [Status] = N'Approve'";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int requestID = rs.getInt("RequestID");
                        int customerID = rs.getInt("CustomerID");
                        String custName = rs.getString("FullName");
                        String farmName = rs.getString("FarmName");
                        String koiTypeName = rs.getString("KoiTypeName");
                        String duration = rs.getString("Duration");
                        int quantity = rs.getInt("Quantity");
                        Date startDate = rs.getDate("StartDate");
                        Date endDate = rs.getDate("EndDate");
                        double quotationPrice = rs.getDouble("QuotationPrice");
                        String status = rs.getString("Status");
                        String managerApprovalStatus = rs.getString("ManagerApprovalStatus");
                        String departureLocation = rs.getString("DepartureLocation");
                        String image = rs.getString("Image");
                        boolean checked = rs.getBoolean("checked");
                        String detailRejected = rs.getString("detailRejected");
                        CustomTourDTO customTourDTO = new CustomTourDTO(requestID, customerID, custName, farmName, koiTypeName, duration, quotationPrice, quantity, startDate, endDate, status, managerApprovalStatus, departureLocation, image, checked, detailRejected);
                        list.add(customTourDTO);
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

    /**
     * GetCustomTourByRequest
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public CustomTourDTO getCustomTourByRequest(int id) throws SQLException, ClassNotFoundException {
        CustomTourDTO result = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [RequestID], [CustomerID], [FullName], [Duration], [StartDate],\n"
                        + "[EndDate], [QuotationPrice], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], \n"
                        + " [KoiTypeName], [Quantity], [Image], [DetailRejected],[Checked] \n"
                        + " FROM [dbo].[CUSTOMTOURREQUEST] \n"
                        + " WHERE [RequestID] = ? AND [Status] = N'Approve'";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int requestID = rs.getInt("RequestID");
                        int customerID = rs.getInt("CustomerID");
                        String custName = rs.getString("FullName");
                        String farmName = rs.getString("FarmName");
                        String koiTypeName = rs.getString("KoiTypeName");
                        String duration = rs.getString("Duration");
                        int quantity = rs.getInt("Quantity");
                        Date startDate = rs.getDate("StartDate");
                        Date endDate = rs.getDate("EndDate");
                        double quotationPrice = rs.getDouble("QuotationPrice");
                        String status = rs.getString("Status");
                        String managerApprovalStatus = rs.getString("ManagerApprovalStatus");
                        String departureLocation = rs.getString("DepartureLocation");
                        String image = rs.getString("Image");
                        boolean checked = rs.getBoolean("checked");
                        String detailRejected = rs.getString("detailRejected");
                        result = new CustomTourDTO(requestID, customerID, custName, farmName, koiTypeName, duration, quotationPrice, quantity, startDate, endDate, status, managerApprovalStatus, departureLocation, image, checked, detailRejected);
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
        return result;
    }

    public static void main(String[] args) throws SQLException {
        int reqID = 2;
        CustomTourDAO dao = new CustomTourDAO();
        boolean dto = dao.sendDetailCustomTourFromSalesToManager(reqID);
        if (dto) {
            System.out.println("Updated.");
        } else {
            System.out.println("Fail.");
        }
    }
}
