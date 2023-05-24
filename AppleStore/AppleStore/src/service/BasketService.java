package src.service;

import src.dao.BasketDAO;
import src.dao.ProductDAO;
import src.vo.BasketVO;
import src.vo.CustomerVO;
import src.vo.ProductVO;

import java.util.Scanner;

public class BasketService implements IBasketService {
	Scanner scanner = new Scanner(System.in);
	BasketDAO basketDAO = new BasketDAO();
	ProductDAO productDAO = new ProductDAO();
	
	@Override
	public void insertBasket(CustomerVO customerVO) {
		System.out.print("추가할 상품 ID를 입력해주세요(없을 시 0) > ");
		int productId = scanner.nextInt();
		scanner.nextLine();
		if(productId == 0) return;
		System.out.print("추가할 상품 개수를 입력해주세요 > ");
		int productCount = scanner.nextInt();
		scanner.nextLine();
		System.out.println();

		ProductVO productVO = new ProductVO();
		productVO.setProductId(productId);
		productVO = productDAO.selectProductByBasket(productVO);
		if (productVO.getProductCount() - productCount < 0) {
			System.out.println("상품재고가 부족합니다.");
		} else {
			BasketVO basketVO = new BasketVO();
			basketVO.setProductId(productId);
			basketVO.setBasketProductCount(productCount);
			int rowCount = basketDAO.insertBasket(customerVO, basketVO);
			if (rowCount == 0) {
				System.out.println("장바구니 추가에 실패했습니다. 관리자 문의 요망 ");
			} else {
				System.out.println("장바구니 추가에 성공했습니다.");
			}
		}
	}

	@Override
	public void selectBaskets(CustomerVO customerVO) {
		basketDAO.selectBaskets(customerVO);
	}

	@Override
	public BasketVO selectOneBasket(BasketVO basketVO) {
		basketVO = basketDAO.selectOneBasket(basketVO);
		return basketVO;
	}

	@Override
	public void updateBasket() {
		System.out.print("수정할 ID를 입력해주세요 > ");
		int basketId = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		System.out.print("수정할 상품 개수를 입력해주세요 > ");
		int productCount = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		BasketVO basketVO = new BasketVO();
		basketVO.setProductId(basketId);
		basketVO.setBasketProductCount(productCount);
		basketDAO.updateBasket(basketVO);
	}

	@Override
	public void deleteBasket() {
		int count = 0;
		String basketDelIdString;
		System.out.print("삭제할 ID를 입력해주세요(스페이스로 구분) > ");
		System.out.println();
		basketDelIdString = scanner.nextLine();
		String[] basketDelIdStringArr = basketDelIdString.split(" ");

		for (int i = 0; i < basketDelIdStringArr.length; i++) {
			BasketVO basketVO = new BasketVO();
			basketVO.setBasketId(Integer.parseInt(basketDelIdStringArr[i]));
			int rowcount = basketDAO.deleteBasket(basketVO);
			count = count + rowcount;
		}
		if (count == basketDelIdStringArr.length) {
			System.out.print(count + "개의 장바구니가 삭제되었습니다.");
		} else {
			System.out.print("장바구니 삭제에 실패했습니다. 관리자 문의 요망");
		}
	}

	@Override
	public void deleteAllBaskets(CustomerVO custmoerVO) {
		System.out.println("장바구니의 모든 데이터를 삭제합니다.");
		int count = basketDAO.deleteAllBaskets(custmoerVO);
		if (count == 0) {
			System.out.println("장바구니 전체 삭제에 실패했습니다. 관리자 문의 요망");
		} else {
			System.out.println("장바구니 전체삭제에 성공했습니다.");
		}
	}
}