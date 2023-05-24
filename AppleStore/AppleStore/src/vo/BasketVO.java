package src.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BasketVO {
	private int basketId;
	private String customerId;
	private int productId;
	private int basketProductCount;
}
