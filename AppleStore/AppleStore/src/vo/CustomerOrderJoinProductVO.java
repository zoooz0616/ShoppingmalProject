package src.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
public class CustomerOrderJoinProductVO {

    private int orderBundleId;
    private int orderId;
    private String customerId;
    private Date orderInputDate;
    private String productName;
    private int orderProductCount;
    private int productPrice;
    private String orderStatusId;
}