package src.dao;

import src.vo.BasketVO;
import src.vo.CustomerVO;

public interface IBasketDAO {
	public int insertBasket(CustomerVO customerVO, BasketVO basketVO);
    public void selectBaskets(CustomerVO customerVO);
    public BasketVO selectOneBasket(BasketVO basketVO);
    public int updateBasket(BasketVO basketVO);
    public int deleteBasket(BasketVO basketVO);
    public int deleteAllBaskets(CustomerVO custmoerVO);
}
