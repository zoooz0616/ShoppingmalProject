package src.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.vo.*;

public class CustomerOrderDAO implements ICustomerOrderDAO {
    @Override
    public int insertCustomerOrder(CustomerVO customerVO, BasketVO basketVO, int orderBundleId) {
        String sql = "insert into customer_order (order_id,customer_id,order_input_date, order_status_id, product_id, order_product_count, order_bundle_id) " +
                "values(applestore_seq.nextval, ?, systimestamp, ?,?,?,?) ";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, "결제대기");
            pstmt.setInt(3,basketVO.getProductId()); // product_id
            pstmt.setInt(4,basketVO.getBasketProductCount()); // order_product_count
            pstmt.setInt(5,orderBundleId);
            rowCount = pstmt.executeUpdate();
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
        return rowCount;
    }

    @Override
    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrder(CustomerVO customerVO) {
        ArrayList<CustomerOrderJoinProductVO> orderArr = new ArrayList<>();
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "select co.order_bundle_id,co.order_id, co.order_input_date, p.product_name, co.order_product_count as count, p.product_price as price, co.order_status_id " +
                "from customer_order co " +
                "left outer join product p " +
                "on co.product_id = p.product_id " +
                "where co.customer_id=? "
                + "order by order_input_date";
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CustomerOrderJoinProductVO customerOrderJoinPriductVO = new CustomerOrderJoinProductVO();
                customerOrderJoinPriductVO.setOrderBundleId(rs.getInt("order_bundle_id"));
                customerOrderJoinPriductVO.setOrderInputDate(rs.getDate("order_input_date"));
                customerOrderJoinPriductVO.setOrderProductCount(rs.getInt("count"));
                customerOrderJoinPriductVO.setProductName(rs.getString("product_name"));
                customerOrderJoinPriductVO.setProductPrice(rs.getInt("price"));
                customerOrderJoinPriductVO.setOrderStatusId(rs.getString("order_status_id"));
                orderArr.add(customerOrderJoinPriductVO);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AppleStoreDataSource.closeConnection(connection);
        }
        return orderArr;
    }

    @Override
    public ArrayList<CustomerOrderJoinProductVO> selectAdminCustomerOrder() { 
        ArrayList<CustomerOrderJoinProductVO> orderArr = new ArrayList<CustomerOrderJoinProductVO>();
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "select co.order_bundle_id,co.customer_id,co.order_id, co.order_input_date, p.product_name, co.order_product_count as count, p.product_price as price, co.order_status_id " +
                    "from customer_order co " +
                    "left outer join product p " +
                    "on co.product_id = p.product_id " +
                    "order by order_input_date ";
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CustomerOrderJoinProductVO customerOrderJoinProductVO = new CustomerOrderJoinProductVO();
                customerOrderJoinProductVO.setOrderBundleId(rs.getInt("order_bundle_id"));
                customerOrderJoinProductVO.setCustomerId(rs.getString("customer_id"));
                customerOrderJoinProductVO.setOrderInputDate(rs.getDate("order_input_date"));
                customerOrderJoinProductVO.setOrderProductCount(rs.getInt("count"));
                customerOrderJoinProductVO.setProductName(rs.getString("product_name"));
                customerOrderJoinProductVO.setProductPrice(rs.getInt("price"));
                customerOrderJoinProductVO.setOrderStatusId(rs.getString("order_status_id"));
                orderArr.add(customerOrderJoinProductVO);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AppleStoreDataSource.closeConnection(connection);
        }
        return orderArr;
    }
    
    @Override
    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrderByCustomerIdCanDelete(CustomerVO customerVO) {
        ArrayList<CustomerOrderJoinProductVO> orderArr = new ArrayList<>();
        String sql = "select co.order_bundle_id,co.order_id, co.order_input_date, p.product_name, co.order_product_count as count, p.product_price as price, co.order_status_id as status " +
                "from customer_order co " +
                "left outer join product p " +
                "on co.product_id = p.product_id " +
                "where co.customer_id=? and (co.order_status_id= ? or co.order_status_id= ?)";
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, "결제대기");
            pstmt.setString(3, "결제완료");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CustomerOrderJoinProductVO customerOrderJoinPriductVO = new CustomerOrderJoinProductVO();
                customerOrderJoinPriductVO.setOrderBundleId(rs.getInt("order_bundle_id"));
                customerOrderJoinPriductVO.setOrderId(rs.getInt("order_id"));
                customerOrderJoinPriductVO.setOrderInputDate(rs.getDate("order_input_date"));
                customerOrderJoinPriductVO.setOrderProductCount(rs.getInt("count"));
                customerOrderJoinPriductVO.setProductName(rs.getString("product_name"));
                customerOrderJoinPriductVO.setProductPrice(rs.getInt("price"));
                customerOrderJoinPriductVO.setOrderStatusId(rs.getString("status"));
                orderArr.add(customerOrderJoinPriductVO);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AppleStoreDataSource.closeConnection(connection);
        }
        return orderArr;
    }
    
    @Override
    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrderIdByBundleIdCanDelete(CustomerOrderVO customerOrderVO) {
        ArrayList<CustomerOrderJoinProductVO> orderArr = new ArrayList<CustomerOrderJoinProductVO>();
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "select order_id, product_id from customer_order where order_bundle_id =?";
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerOrderVO.getOrderBundleId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CustomerOrderJoinProductVO customerOrderJoinPriductVO = new CustomerOrderJoinProductVO();
                customerOrderJoinPriductVO.setOrderId(rs.getInt("order_id"));
                customerOrderJoinPriductVO.setOrderProductCount(rs.getInt("product_id"));
                orderArr.add(customerOrderJoinPriductVO);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AppleStoreDataSource.closeConnection(connection);
        }
        return orderArr;
    }
    
    @Override
    public int updateOrderStatusId(CustomerOrderVO customerOrderVO) {
        String sql = "update customer_order set order_status_id =? , order_update_date= systimestamp where order_bundle_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerOrderVO.getOrderStatusId());
            pstmt.setInt(2, customerOrderVO.getOrderBundleId());
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
    public int deleteCustomerOrder(CustomerOrderVO customerOrderVO) {
        String sql = "update customer_order set order_status_id=?, order_delete_date=systimestamp where order_bundle_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "주문취소");
            pstmt.setInt(2, customerOrderVO.getOrderBundleId());
            rowCount = pstmt.executeUpdate();
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
        return rowCount;
    }
}


