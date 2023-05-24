package src.vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerVO {
	private String customerId;
	private String customerPassword;
	private String customerName;
	private int customerPhoneNumber;
	private String customerAddress;
	private Date customerBornDate;
	private String customerSex;
	private Date customerInputDate;
	private Date customerUpdateDate;
	private Date customerDeleteDate;
}
