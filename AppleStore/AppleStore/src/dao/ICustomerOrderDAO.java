package src.dao;

import java.util.ArrayList;

import src.vo.BasketVO;
import src.vo.CustomerOrderJoinProductVO;
import src.vo.CustomerOrderVO;
import src.vo.CustomerVO;

public interface ICustomerOrderDAO {
	public int insertCustomerOrder(CustomerVO customerVO, BasketVO basketVO, int orderBundleId);
	public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrder(CustomerVO customerVO);
	public ArrayList<CustomerOrderJoinProductVO> selectAdminCustomerOrder();
	public int deleteCustomerOrder(CustomerOrderVO customerOrderVO);
	public int updateOrderStatusId(CustomerOrderVO customerOrderVO);
	public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrderByCustomerIdCanDelete(CustomerVO customerVO);
    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrderIdByBundleIdCanDelete(CustomerOrderVO customerOrderVO);
}
