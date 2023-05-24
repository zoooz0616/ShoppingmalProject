package src.vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
public class ProductVO {
    private int productId;
    private int categoryId;
    private String productName;
    private int productPrice;
    private Date productInputDate;
    private Date productUpdateDate;
    private Date productDeleteDate;
    private String productImage;
    private int productCount;
    private int totalOrderCount;

    public String toString() {
    	return String.format("%5d\t%10s\t%7d\t%7d", productId, productName, productPrice, productCount);
    }

}
