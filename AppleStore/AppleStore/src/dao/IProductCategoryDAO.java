package src.dao;

import src.vo.ProductCategoryVO;

import java.util.ArrayList;

public interface IProductCategoryDAO {
    public void insertProductCategory(ProductCategoryVO productCategoryVO);
    public ArrayList<ProductCategoryVO> selectAllProductCategory();
    public ArrayList<String> getAllColumnNamesProductCategory();
    public int updateProductCategory(ProductCategoryVO productCategoryVO);
    public int deleteProductCategory(ProductCategoryVO productCategoryVO);
}
