package src.dao;

import src.vo.AppleStoreDataSource;
import src.vo.ProductCategoryVO;

import java.sql.*;
import java.util.ArrayList;

public class ProductCategoryDAO implements IProductCategoryDAO {
    @Override
    public void insertProductCategory(ProductCategoryVO productCategoryVO) {
        String sql = "insert into product_category (CATEGORY_ID, CATEGORY_NAME, CATEGORY_INPUT_DATE) values(applestore_seq.nextval, ?,systimestamp )";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productCategoryVO.getCategoryName());
            pstmt.executeUpdate();
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
    public ArrayList<ProductCategoryVO> selectAllProductCategory() {
        ArrayList<ProductCategoryVO> productCategoryList = new ArrayList<ProductCategoryVO>();
        String sql = "select category_id, category_name from product_category where category_delete_date is null";
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
        	connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductCategoryVO productCategoryVO = new ProductCategoryVO();
                productCategoryVO.setCategoryId(rs.getInt("category_id"));
                productCategoryVO.setCategoryName(rs.getString("category_name"));
                productCategoryList.add(productCategoryVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return productCategoryList;
    }
    
    @Override
    public ArrayList<String> getAllColumnNamesProductCategory() {
        ArrayList<String> columnNames = new ArrayList<String>();
        String sql = "select category_id, category_name from product_category";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                columnNames.add(metaData.getColumnName(i + 1).toUpperCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return columnNames;
    }

    @Override
    public int updateProductCategory(ProductCategoryVO productCategoryVO) {
        int count = 0;
        String sql = "update product_category set category_name =?, category_update_date = systimestamp where category_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productCategoryVO.getCategoryName());
            pstmt.setInt(2, productCategoryVO.getCategoryId());
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
    public int deleteProductCategory(ProductCategoryVO productCategoryVO) {
        String sql = "  update product_category set category_delete_date =systimestamp where category_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, productCategoryVO.getCategoryId());
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
