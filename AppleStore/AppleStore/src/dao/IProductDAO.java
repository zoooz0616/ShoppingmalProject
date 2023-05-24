package src.dao;

import java.util.ArrayList;

import src.vo.BasketVO;
import src.vo.CustomerOrderVO;
import src.vo.ProductCategoryVO;
import src.vo.ProductVO;

public interface IProductDAO {
	public int insertProduct(ProductVO productVO);
	public ArrayList<ProductVO> selectCategoryProduct(ProductCategoryVO productCategoryVO);
    public ProductVO selectProductByBasket(ProductVO productVO);
    public ArrayList<ProductVO> selectPopularProduct();
    public ArrayList<ProductVO> selectLowPriceProduct();
    public ArrayList<ProductVO> selectHighPriceProduct();
    public int updateProduct(ProductVO productVO);
    public int updateProductCountIncrease(CustomerOrderVO customerOrderVO);
    public int updateProductCountDecrease(BasketVO basketVO);
    public int deleteProduct(ProductVO productVO);
}


