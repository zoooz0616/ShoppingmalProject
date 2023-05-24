package src.service;

import src.vo.ProductCategoryVO;
import src.vo.ProductVO;

public interface IProductService {
	public void insertProduct();
	public void selectCategoryProduct();
	public void selectPopularProduct();
	public void selectLowPriceProduct();
	public void selectHighPriceProduct();
	public void updateProduct();
	public void deleteProduct();
}
