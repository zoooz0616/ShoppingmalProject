package src.vo;
import java.sql.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductCategoryVO {
private int categoryId;
private String categoryName;
private Date categoryInputDate;
private Date categoryUpdateDate;
private Date categoryDeleteDate;

}
