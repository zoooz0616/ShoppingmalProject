package src.dao;

import src.vo.AppleStoreDataSource;
import src.vo.BasketVO;
import src.vo.CustomerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketDAO implements IBasketDAO {

    @Override
    public int insertBasket(CustomerVO customerVO, BasketVO basketVO) {
        int rowCount = 0;
        String sql = "insert into basket (basket_id, basket_product_count, customer_id, product_id) values(applestore_seq.nextval, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, basketVO.getBasketProductCount());
            pstmt.setString(2, customerVO.getCustomerId());
            pstmt.setInt(3, basketVO.getProductId());
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
    public void selectBaskets(CustomerVO customerVO) {
        String sql = "select b.basket_id, b.basket_product_count, p.product_name, p.product_price "
                + "from basket b "
                + "left join product p "
                + "on b.product_id = p.product_id "
                + "where b.customer_id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            rs = pstmt.executeQuery();

            System.out.println("---------------------------");
            System.out.printf("%3s\t%5s\t%5s\t%5s", "ID", "제품이름", "제품가격", "개수\n");
            System.out.println("---------------------------");
            while (rs.next()) {
                int basketId = rs.getInt("basket_id");
                String productName = rs.getString("product_name");
                int productPrice = rs.getInt("product_price");
                int basketProductCount = rs.getInt("basket_product_count");
                System.out.printf("%3s\t%5s\t%5s\t%3s", basketId, productName, productPrice, basketProductCount);
                System.out.println();
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
    public BasketVO selectOneBasket(BasketVO basketVO) {
        String sql = "select basket_id, basket_product_count, customer_id, product_id from basket where basket_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, basketVO.getBasketId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                basketVO.setBasketId(rs.getInt("basket_id"));
                basketVO.setBasketProductCount(rs.getInt("basket_product_count"));
                basketVO.setCustomerId(rs.getString("customer_id"));
                basketVO.setProductId(rs.getInt("product_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return basketVO;
    }
    
    @Override
    public int updateBasket(BasketVO basketVO) {
        String sql = "update basket set basket_product_count=? "
                + "where basket_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, basketVO.getBasketProductCount());
            pstmt.setInt(2, basketVO.getProductId());
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
    public int deleteBasket(BasketVO basketVO) {
        String sql = "delete from basket where basket_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, basketVO.getBasketId());
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
    public int deleteAllBaskets(CustomerVO custmoerVO) {
        String sql = "delete from basket where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, custmoerVO.getCustomerId());
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
