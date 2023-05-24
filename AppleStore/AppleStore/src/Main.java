package src;

import java.util.Scanner;

import src.dao.ProductDAO;
import src.service.AdminService;
import src.service.BasketService;
import src.service.CustomerOrderService;
import src.service.CustomerService;
import src.service.ProductCategoryService;
import src.service.ProductService;
import src.vo.AdminVO;
import src.vo.CustomerVO;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static CustomerVO customerVO = null;
	static AdminVO adminVO = null;
	static AdminService adminService = new AdminService();
	static CustomerService customerService = new CustomerService();
	static BasketService basketService = new BasketService();
	static CustomerOrderService customerOrderService = new CustomerOrderService();
	static boolean isAdmin = false;
	static boolean isCustomer = false;

	public static void welcomeMenu() {
		scanner = new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("1. 로그인 | 2. 관리자 로그인 | 3. 회원가입");
		System.out.println("-------------------------------");
		System.out.print("메뉴 > ");
		int menu = scanner.nextInt();
		scanner.nextLine();
		switch (menu) {
		case 1:
			customerVO = customerService.customerLogin();
			if (customerVO != null) {
				isCustomer = true;
				customerMenu();
			} else
				welcomeMenu();
			break;
		case 2:
			adminVO = adminService.adminLogin();
			if (adminVO != null) {
				isAdmin = true;
				adminMenu();
			} else
				welcomeMenu();
			break;
		case 3:
			customerService.customerJoin();
			welcomeMenu();
		default:
			break;
		}
		scanner.close();
	}

	public static void customerMenu() {
		scanner = new Scanner(System.in);
		System.out.println("---------------------------------------------");
		System.out.println("1.제품보기 | 2.장바구니 | 3.주문확인 | 4.회원관리 | 5.로그아웃");
		System.out.println("---------------------------------------------");
		System.out.print("메뉴 > ");
		int menu = scanner.nextInt();
		scanner.nextLine();
		switch (menu) {
		case 1:
			productSelect();
			new BasketService().insertBasket(customerVO);
			customerMenu();
			break;
		case 2:
			basketService.selectBaskets(customerVO);
			System.out.println("-----------------------------------------");
			System.out.println("1.장바구니 수정| 2.장바구니 삭제 | 3.주문하기 | 4.뒤로가기");
			System.out.println("-----------------------------------------");
			System.out.print("메뉴 > ");
			int num = scanner.nextInt();
			scanner.nextLine();
			switch (num) {
			case 1:
				basketService.updateBasket();
				break;

			case 2:
				basketService.deleteBasket();
				break;
			case 3:
				new CustomerOrderService().insertCustomerOrder(customerVO);
				break;
			case 4:
				break;
			default:
				System.out.println("메뉴를 잘못입력하셨습니다.");
				break;
			}
			customerMenu();
			break;

		case 3:
			customerOrderService.selectCustomerOrder(customerVO);
			customerMenu();
			break;

		case 4:
			isCustomer = customerService.checkCustomerPassword(customerVO);
			if (isCustomer == true) {
				System.out.println("---------------------------------------------------");
				System.out.println("1.비밀번호 변경| 2.이름 변경 | 3.전화번호 변경 | 4.주소 변경 | 5.회원탈퇴");
				System.out.println("--------------------------------------------------");
				System.out.print("메뉴 > ");
				int submenu = scanner.nextInt();
				scanner.nextLine();
				switch (submenu) {
				case 1:
					customerService.customerUpdatePassword(customerVO);
					break;
				case 2:
					customerService.customerUpdateName(customerVO);
					break;
				case 3:
					customerService.customerUpdatePhoneNumber(customerVO);
					break;
				case 4:
					customerService.customerUpdateAddress(customerVO);
					break;
				case 5:
					customerService.customerDelete(customerVO);
					break;
				default:
					break;
				}
			}
			customerMenu();
			break;
		case 5:
			System.out.println("로그아웃되었습니다.");
			welcomeMenu();
			break;
		default:
			break;
		}
		scanner.close();
	}

	public static void adminMenu() {
		scanner = new Scanner(System.in);
		System.out.println("-----------------------------------------------");
		System.out.println("1.상품관리 | 2.주문관리 | 3.카테고리관리 | 4.회원관리 | 5.로그아웃");
		System.out.println("-----------------------------------------------");
		System.out.print("메뉴 > ");
		int menu = scanner.nextInt();
		scanner.nextLine();
		switch (menu) {
		case 1:
			productManage();
			adminMenu();
			break;
		case 2:
			orderManage();
			adminMenu();
			break;
		case 3:
			categoryManage();
			break;
		case 4:
			customerManage();
			adminMenu();
			break;
		case 5:
			System.out.println("로그아웃되었습니다.");
			welcomeMenu();
			break;
		default:
			break;
		}
		scanner.close();
	}

	public void customerLogin() {
		CustomerService customerService = new CustomerService();
		customerService.customerLogin();
	}

	public void cunstomerPasswordUpdate() {
		CustomerVO customerVO = new CustomerVO();
		CustomerService customerService = new CustomerService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		customerService.customerUpdatePassword(customerVO);
	}

	public void customerNameUpdate() {
		CustomerVO customerVO = new CustomerVO();
		CustomerService customerService = new CustomerService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		customerService.customerUpdateName(customerVO);
	}

	public void customerPhoneNumberUpdate() {
		CustomerVO customerVO = new CustomerVO();
		CustomerService customerService = new CustomerService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		customerService.customerUpdatePhoneNumber(customerVO);
	}

	public void customerUpdateAddress() {
		CustomerVO customerVO = new CustomerVO();
		CustomerService customerService = new CustomerService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		customerService.customerUpdateAddress(customerVO);
	}

	public void customerDelete() {
		CustomerVO customerVO = new CustomerVO();
		CustomerService customerService = new CustomerService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		customerService.customerDelete(customerVO);
	}
	// customer end--------------------------------------

	// productCategory --------------------------------------
	public void insertProductCategory() {
		ProductCategoryService productCategoryService = new ProductCategoryService();
		productCategoryService.insertProductCategory();
	}

	public static void selectAllProductCategory() {
		ProductCategoryService productCategoryService = new ProductCategoryService();
		productCategoryService.selectAllProductCategory();
	}

	public void updateProductCategory() {
		ProductCategoryService productCategoryService = new ProductCategoryService();
		productCategoryService.updateProductCategory();
	}

	public void deleteProductCategory() {
		ProductCategoryService productCategoryService = new ProductCategoryService();
		productCategoryService.deleteProductCategory();
	}
	// productCategory end --------------------------------------

	// basket ---------------------------------
	public void insertBasket() {
		CustomerVO customerVO = new CustomerVO();
		BasketService basketService = new BasketService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		basketService.insertBasket(customerVO);
	}

	public static void selectBasket() {
		CustomerVO customerVO = new CustomerVO();
		BasketService basketService = new BasketService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test");
		basketService.selectBaskets(customerVO);
	}

	public static void updateBasket() {
		BasketService basketService = new BasketService();
		basketService.updateBasket();
	}

	public static void deleteBasket() {
		BasketService basketService = new BasketService();
		basketService.deleteBasket();
	}

	// basket end ---------------------------------

	// customerOrder -------------------------------------------
	public static void insertCustomerOrder() {
		CustomerVO customerVO = new CustomerVO();
		CustomerOrderService customerOrderService = new CustomerOrderService();
		// 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
		customerVO.setCustomerId("test"); // 지금 이 유저는 로그인만 막아놔서 다른건 수정 가능함
		customerOrderService.insertCustomerOrder(customerVO);
	}

	// customerOrder end -------------------------------------------
	public static void productManage() {
		System.out.println("---------------------------------------------");
		System.out.println("1.상품확인 | 2.상품추가 | 3.상품수정 | 4.상품삭제 | 5.뒤로가기");
		System.out.println("---------------------------------------------");
		scanner = new Scanner(System.in);
		System.out.print("메뉴 > ");
		int menu = scanner.nextInt();
		scanner.nextLine();
		switch (menu) {
		case 1:
			productSelect();
			break;
		case 2:
			new ProductService().insertProduct();
			break;
		case 3:
			new ProductService().updateProduct();
			break;

		case 4:
			new ProductService().deleteProduct();
			break;

		case 5:
			return;
		default:
			System.out.println("메뉴를 잘못입력하였습니다.");
			break;
		}
	}

	public static void productSelect() {
		System.out.println("--------------------------------------");
		System.out.println("1.카테고리별 | 2.인기순 | 3.가격낮은순 | 4.가격높은순");
		System.out.println("--------------------------------------");
		System.out.print("메뉴 > ");
		int submenu = scanner.nextInt();
		scanner.nextLine();
		switch (submenu) {
		case 1:
			new ProductService().selectCategoryProduct();
			break;
		case 2:
			new ProductService().selectPopularProduct();
			break;
		case 3:
			new ProductService().selectLowPriceProduct();
			break;
		case 4:
			new ProductService().selectHighPriceProduct();
			break;
		default:
			System.out.println("메뉴를 잘못입력하셨습니다.");
			break;
		}
	}

    public static void orderManage() {
    	customerOrderService.selectAdminCustomerOrder();
    	customerOrderService.updateOrderStatusId();
    }

    public static void categoryManage() {
    	System.out.println("-----------------------------------");
        System.out.println("1.카테고리 추가 | 2.카테고리 수정 | 3.카테고리 삭제");
        System.out.println("-----------------------------------");
        System.out.print("메뉴 > ");
        int menu = scanner.nextInt();
        ProductCategoryService productCategoryService = new ProductCategoryService();
        scanner.nextLine();
        switch (menu) {
		case 1:
			productCategoryService.insertProductCategory();
			break;
		case 2:
			productCategoryService.updateProductCategory();
		case 3:
			productCategoryService.deleteProductCategory();
		default:
			System.out.println("메뉴를 잘못 입력했습니다.");
			break;
		}
        
    }
    
    public static void customerManage() {

    }

    
	public static void main(String[] args) {
		welcomeMenu();
	}

}