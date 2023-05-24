package src.dao;

import src.vo.AppleStoreDataSource;
import src.vo.CustomerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO implements ICustomerDAO {
    boolean isConfirmedPassword = false;

    @Override
    public CustomerVO customerLogin(CustomerVO customerVO) {
        String sql = "select customer_id, customer_delete_date from customer where customer_id = ? and customer_password=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, customerVO.getCustomerPassword());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                customerVO.setCustomerId(rs.getString("customer_id"));
                customerVO.setCustomerDeleteDate(rs.getDate("customer_delete_date"));
                return customerVO;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
    }

    @Override
    public int customerJoin(CustomerVO customerVO) {
        String sql = "insert into customer (customer_id, customer_password, customer_name, customer_phone_number, customer_address, customer_born_date, customer_sex,customer_join_date) " +
                "values(?,?, ?, ?, ?, ?, ?, systimestamp)";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, customerVO.getCustomerPassword());
            pstmt.setString(3, customerVO.getCustomerName());
            pstmt.setInt(4, customerVO.getCustomerPhoneNumber());
            pstmt.setString(5, customerVO.getCustomerAddress());
            pstmt.setDate(6, customerVO.getCustomerBornDate());
            pstmt.setString(7, customerVO.getCustomerSex());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return count;
    }

    @Override
    public CustomerVO checkCustomerPassword(CustomerVO customerVO) {
        String sql = "select customer_id,customer_password from customer "
        		+ "where customer_id = ? and customer_password=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                customerVO.setCustomerId(rs.getString("customer_id"));
                customerVO.setCustomerPassword(rs.getString("customer_password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {

                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return customerVO;
    }

    @Override
    public int customerUpdatePassword(CustomerVO customerVO) {
        String sql = "update customer set customer_password =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerPassword());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return count;
    }

    @Override
    public int customerUpdateName(CustomerVO customerVO) {
        String sql = "update customer set customer_name =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerName());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return count;
    }

    @Override
    public int customerUpdatePhoneNumber(CustomerVO customerVO) {
        String sql = "update customer set customer_Phone_number =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerVO.getCustomerPhoneNumber());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return count;
    }

    @Override
    public int customerUpdateAddress(CustomerVO customerVO) {
        String sql = "update customer set customer_address =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerAddress());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return count;
    }

    @Override
    public int customerDelete(CustomerVO customerVO) {
        String sql = "update customer set customer_delete_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {

                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return count;
    }
}
