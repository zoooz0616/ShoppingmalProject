package src.service;

import src.vo.BasketVO;
import src.vo.CustomerVO;

public interface IBasketService {
	public void insertBasket(CustomerVO customerVO);
	public void selectBaskets(CustomerVO customerVO);
	public BasketVO selectOneBasket(BasketVO basketVO);
	public void updateBasket();
	public void deleteBasket();
	public void deleteAllBaskets(CustomerVO custmoerVO);
}
