package src.service;

import java.util.ArrayList;
import java.util.Scanner;

import src.dao.ProductCategoryDAO;
import src.dao.ProductDAO;
import src.vo.ProductCategoryVO;
import src.vo.ProductVO;

public class ProductService implements IProductService {
	Scanner scanner = new Scanner(System.in);
    ProductDAO productDAO = new ProductDAO();
    ProductCategoryService productCategoryService = new ProductCategoryService();
    @Override
    public void insertProduct() {
    	ProductVO productVO = new ProductVO();
        System.out.println("[새로운 상품의 정보를 입력하세요.]");
        System.out.print("상품 이름 > ");
        productVO.setProductName(scanner.nextLine());
        ArrayList<ProductCategoryVO> productCategoryList = new ArrayList<>();
        productCategoryList = new ProductCategoryDAO().selectAllProductCategory();
        for (ProductCategoryVO productCategory : productCategoryList) {
            System.out.print(productCategory.getCategoryId() + "." + productCategory.getCategoryName() + " |");
        }
        System.out.println();
        System.out.print("상품의 카테고리 > ");
        productVO.setCategoryId(scanner.nextInt());
        System.out.print("상품 가격 > ");
        productVO.setProductPrice(scanner.nextInt());
        scanner.nextLine();
        System.out.print("상품 이미지 > ");
        productVO.setProductImage(scanner.nextLine());
        System.out.print("상품의 개수 > ");
        productVO.setProductCount(scanner.nextInt());
        int resultCount = productDAO.insertProduct(productVO);
        System.out.println(resultCount + "개의 상품이 추가되었습니다.");
    }

    @Override
    public void selectCategoryProduct() {
        ArrayList<ProductVO> categoryProductArr = new ArrayList<ProductVO>();
        productCategoryService.selectAllProductCategory();
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        ProductCategoryVO productCategoryVO = new ProductCategoryVO();
        productCategoryVO.setCategoryId(categoryId);
        System.out.println("---------------------------------------");
        System.out.printf("%5s\t%12s\t%5s\t%7s", "제품ID", "제품이름", "제품가격", "남은수량");
        System.out.println();
        System.out.println("---------------------------------------");
        categoryProductArr = productDAO.selectCategoryProduct(productCategoryVO);
        for (int i = 0; i < categoryProductArr.size(); i++) {
            System.out.printf("%5s\t%8s\t%5s\t%7s", categoryProductArr.get(i).getProductId(),
                    categoryProductArr.get(i).getProductName(),
                    categoryProductArr.get(i).getProductPrice(),
                    categoryProductArr.get(i).getProductCount());
            System.out.println();
        }
    }

    @Override
    public void selectPopularProduct() { // TODO 해수 테이블 밀고 다시 테스트
        ArrayList<ProductVO> popularProductArr = new ArrayList<ProductVO>();
        System.out.println("------------------------------------------------");
        System.out.printf("%5s\t%12s\t%5s\t%7s\t%7s", "제품ID", "제품이름", "제품가격", "남은수량", "주문수량");
        System.out.println();
        System.out.println("------------------------------------------------");
        popularProductArr = productDAO.selectPopularProduct();
        for (int i = 0; i < popularProductArr.size(); i++) {
            System.out.printf("%5s\t%8s\t%5s\t%7s\t%7s",
                    popularProductArr.get(i).getProductId(),
                    popularProductArr.get(i).getProductName(),
                    popularProductArr.get(i).getProductPrice(),
                    popularProductArr.get(i).getProductCount(),
                    popularProductArr.get(i).getTotalOrderCount()
            );
            System.out.println();
        }
    }

    @Override
    public void selectLowPriceProduct() {
        ArrayList<ProductVO> lowPriceProductArr = new ArrayList<ProductVO>();
        System.out.println("---------------------------------------");
        System.out.printf("%5s\t%12s\t%5s\t%7s", "제품ID", "제품이름", "제품가격", "남은수량");
        System.out.println();
        System.out.println("---------------------------------------");
        lowPriceProductArr = productDAO.selectLowPriceProduct();
        for (int i = 0; i < lowPriceProductArr.size(); i++) {
            System.out.printf("%5s\t%8s\t%5s\t%7s",
                    lowPriceProductArr.get(i).getProductId(),
                    lowPriceProductArr.get(i).getProductName(),
                    lowPriceProductArr.get(i).getProductPrice(),
                    lowPriceProductArr.get(i).getProductCount());
            System.out.println();
        }
    }

    @Override
    public void selectHighPriceProduct() {
        ArrayList<ProductVO> highPriceProductArr = new ArrayList<ProductVO>();
        System.out.println("---------------------------------------");
        System.out.printf("%10s\t%12s\t%7s\t%7s", "제품ID", "제품이름", "제품가격", "남은수량");
        System.out.println();
        System.out.println("---------------------------------------");
        highPriceProductArr = productDAO.selectHighPriceProduct();
        for (int i = 0; i < highPriceProductArr.size(); i++) {
            System.out.printf("%5s\t%8s\t%5s\t%7s",
                    highPriceProductArr.get(i).getProductId(),
                    highPriceProductArr.get(i).getProductName(),
                    highPriceProductArr.get(i).getProductPrice(),
                    highPriceProductArr.get(i).getProductCount());
            System.out.println();
        }
    }


    @Override
    public void updateProduct() {
        ProductVO productVO = new ProductVO();
        System.out.print("수정할 상품의 ID > ");
        int updateProductId = scanner.nextInt();
        scanner.nextLine();
        productVO.setProductId(updateProductId);
        System.out.print("상품 이름 > ");
        String productName = scanner.nextLine();
        productVO.setProductName(productName);
        ArrayList<ProductCategoryVO> productCategoryList = new ArrayList<>();
        productCategoryList = new ProductCategoryDAO().selectAllProductCategory();
        System.out.println("---------------------------------------------------------------------");
        for (ProductCategoryVO productCategory : productCategoryList) {
            System.out.print(productCategory.getCategoryId() + "." + productCategory.getCategoryName() + " |");
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.print("상품의 카테고리 > ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        productVO.setCategoryId(categoryId);
        System.out.print("상품 가격 > ");
        int productPrice = scanner.nextInt();
        scanner.nextLine();
        int productCount= scanner.nextInt();
        scanner.nextLine();
        productVO.setProductCount(productCount);
        productVO.setProductPrice(productPrice);
        int updateCount = productDAO.updateProduct(productVO);
        System.out.println(updateCount + "개의 상품이 수정되었습니다.");

    }

    @Override
    public void deleteProduct() {
        ProductVO productVO = new ProductVO();
        System.out.print("삭제할 상품의 ID > ");
        int deleteProductId = scanner.nextInt();
        scanner.nextLine();
        productVO.setProductId(deleteProductId);
        int deleteCount = productDAO.deleteProduct(productVO);
        System.out.println(deleteCount + "개의 상품을 삭제했습니다.");
    }
}
