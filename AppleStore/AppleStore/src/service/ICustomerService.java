package src.service;

import src.vo.CustomerVO;

public interface ICustomerService {
	public CustomerVO customerLogin();
	public void customerJoin();
	public boolean checkCustomerPassword(CustomerVO customerVO);
	public void customerUpdatePassword(CustomerVO customerVO);
	public void customerUpdateName(CustomerVO customerVO);
	public void customerUpdatePhoneNumber(CustomerVO customerVO);
	public void customerUpdateAddress(CustomerVO customerVO);
	public void customerDelete(CustomerVO customerVO);
}
