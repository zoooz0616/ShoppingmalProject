package src.dao;

import src.vo.CustomerVO;

public interface ICustomerDAO {
    public CustomerVO customerLogin(CustomerVO customerVO);
    public int customerJoin(CustomerVO customerVO);
    public CustomerVO  checkCustomerPassword(CustomerVO customerVO);
    public int customerUpdatePassword(CustomerVO customerVO);
    public int customerUpdateName(CustomerVO customerVO);
    public int customerUpdatePhoneNumber(CustomerVO customerVO);
    public int customerUpdateAddress(CustomerVO customerVO);
    public int customerDelete(CustomerVO customerVO);
}
