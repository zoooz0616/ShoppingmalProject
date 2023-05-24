package src.service;

import src.vo.CustomerVO;

public interface ICustomerOrderService {
	public void insertCustomerOrder(CustomerVO customerVO);
    public void selectCustomerOrder(CustomerVO customerVO);
    public void selectCustomerOrderCanDelete(CustomerVO customerVO);
    public void selectAdminCustomerOrder();
    public void updateOrderStatusId();
    public void deleteCustomerOrder(CustomerVO customerVO);
}
