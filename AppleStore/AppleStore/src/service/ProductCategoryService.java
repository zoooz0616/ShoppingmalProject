package src.service;

import src.dao.ProductCategoryDAO;
import src.vo.ProductCategoryVO;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductCategoryService implements IProductCategoryService{
    Scanner scanner = new Scanner(System.in);
    ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();

    @Override
    public void insertProductCategory() {
        System.out.println("");
        System.out.println("카테고리 이름을 입력해주세요 >");
        String categoryName = scanner.nextLine();
        ProductCategoryVO productCategoryVO = new ProductCategoryVO();
        productCategoryVO.setCategoryName(categoryName);
        productCategoryDAO.insertProductCategory(productCategoryVO);
    }

    @Override
    public void selectAllProductCategory() {
        ArrayList<ProductCategoryVO> productCategories = productCategoryDAO.selectAllProductCategory();
        ArrayList<String> columns = productCategoryDAO.getAllColumnNamesProductCategory();
        System.out.println(columns);
        for (ProductCategoryVO i : productCategories) {
            System.out.printf("%12d | %s %n", i.getCategoryId(), i.getCategoryName());
        }
    }

    @Override
    public void updateProductCategory() {
        int count = 0;
        int categoryId;
        System.out.print("수정할 카테고리의 아이디를 입력해주세요 >");
        System.out.println();
        categoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("바뀔 카테고리 이름을 입력해주세요 >");
        System.out.println();
        String categoryName = scanner.nextLine();
        ProductCategoryVO productCategoryVO = new ProductCategoryVO();
        productCategoryVO.setCategoryId(categoryId);
        productCategoryVO.setCategoryName(categoryName);
        count = productCategoryDAO.updateProductCategory(productCategoryVO);
        if (count == 1) {
            System.out.println("카테고리 이름이 바뀌었습니다.");
        } else {
            System.out.println("문제발생 관리자에게 문의하세요.");
        }
    }

    @Override
    public void deleteProductCategory() {
        int count = 0;
        System.out.print("삭제할 카테고리의 아이디를 입력해주세요 >");
        System.out.println();
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        ProductCategoryVO productCategoryVO = new ProductCategoryVO();
        productCategoryVO.setCategoryId(categoryId);
        count = productCategoryDAO.deleteProductCategory(productCategoryVO);
        if (count == 1) {
            System.out.println("카테고리가 삭제되었습니다.");
        } else {
            System.out.println("문제발생 관리자에게 문의하세요.");
        }
    }

}
