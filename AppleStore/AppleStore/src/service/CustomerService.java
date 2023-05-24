package src.service;

import src.dao.CustomerDAO;
import src.dao.ProductCategoryDAO;
import src.dao.ProductDAO;
import src.vo.CustomerVO;
import src.vo.ProductCategoryVO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService implements ICustomerService {
	Scanner scanner = new Scanner(System.in);
	CustomerDAO customerDAO = new CustomerDAO();
	ProductDAO productDAO = new ProductDAO();
	BasketService basketService = new BasketService();
	boolean isCustomerLogin = false;
	CustomerVO customerVOrealData = null;

	@Override
	public CustomerVO customerLogin() {
		String inputCustomerId;
		String inputCustomerPassword;
		System.out.println("[ 로그인 ]");
		System.out.print("아이디 > ");
		inputCustomerId = scanner.nextLine();
		System.out.print("비밀번호 > ");
		inputCustomerPassword = scanner.nextLine();
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerId(inputCustomerId);
		customerVO.setCustomerPassword(inputCustomerPassword);
		customerVOrealData = customerDAO.customerLogin(customerVO);

		if (customerVOrealData != null) {
            if (customerVOrealData.getCustomerDeleteDate() != null) { // 값이 있으면 로그인 실패
                isCustomerLogin = false;
                System.out.println("로그인에 실패했습니다.");
                return null;
            } else if (customerVOrealData.getCustomerId().equals(inputCustomerId)) {
                isCustomerLogin = true;
                System.out.println("로그인에 성공했습니다.");
                return customerVOrealData;
            } else {
                isCustomerLogin = false;
                System.out.println("로그인에 실패했습니다."); //TODO 로직 뭐 하나 없앨 수 있지 않아? 조건을 좀 줄일 수 있지 않아?
                return null;
            }
        } else {
            System.out.println("로그인에 실패했습니다.");
            return null;
        }
	}

	@Override
	public void customerJoin() {
		int count = 0;
		String customerId;
		String customerPassword;
		String customerName;
		int customerPhoneNumber;
		String customerAddress;
		String customerBornDate;
		String customerSex;

		System.out.println("회원가입을 시작합니다.");
		System.out.print("아이디 입력 >");
		customerId = scanner.nextLine();
		while (true) {
	         System.out.print("비밀번호 입력(영문+숫자 8자 이상) > ");
	         customerPassword = scanner.nextLine();
	         Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"); // 8자 영문+숫자
	         Matcher passMatcher = passPattern.matcher(customerPassword);
	         if (!passMatcher.find()) {
	            System.out.println("비밀번호는 영문+숫자 8자로 구성되어야 합니다");
	            System.out.println("비밀번호를 다시 입력하세요.");
	            continue;
	         } else
	            break;
	      }
		System.out.print("이름 입력 > ");
		customerName = scanner.nextLine();
		System.out.println();
		System.out.print("전화번호 숫자만 입력 > ");
		customerPhoneNumber = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		System.out.print("주소 입력 > ");
		customerAddress = scanner.nextLine();
		System.out.println();
		System.out.print("생년월일 입력(1999-10-12) > ");
		customerBornDate = scanner.nextLine();
		System.out.println();
		System.out.print("성별 입력 (F, M) > ");
		customerSex = scanner.nextLine();
		System.out.println();

		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerId(customerId);
		customerVO.setCustomerPassword(customerPassword);
		customerVO.setCustomerName(customerName);
		customerVO.setCustomerPhoneNumber(customerPhoneNumber);
		customerVO.setCustomerAddress(customerAddress);
		customerVO.setCustomerBornDate(Date.valueOf(customerBornDate));
		customerVO.setCustomerSex(customerSex);
		count = customerDAO.customerJoin(customerVO);
		if (count == 0) {
			System.out.println("회원가입이 완료되었습니다.");
		} else {
			System.out.println("회원가입이 실패했습니다.");
		}
	}

	@Override
	public boolean checkCustomerPassword(CustomerVO customerVO) {
		String customerPassword;
		boolean isConfirmedPassword = false;
		System.out.print("기존 비밀번호를 입력해주세요 > ");
		customerPassword = scanner.nextLine();
		String inputCustomerPassword = customerPassword;
		customerVO.setCustomerPassword(customerPassword);
		customerVO = customerDAO.checkCustomerPassword(customerVO);
		String selectCustomerPassword = customerVO.getCustomerPassword();
		if (inputCustomerPassword == selectCustomerPassword) {
			System.out.println("기존 비밀번호를 확인했습니다.");
			isConfirmedPassword=true;
		} else {
			System.out.println("기존 비밀번호가 일치하지 않습니다.");
			System.exit(0);
		}
		return isConfirmedPassword;
	}

	@Override
	public void customerUpdatePassword(CustomerVO customerVO) {
		int count = 0;
		System.out.println("비밀번호를 변경합니다.");
		checkCustomerPassword(customerVO);
		String newPassword;
		System.out.print("새 비밀번호를 입력해주세요 > ");
		newPassword = scanner.nextLine();
		customerVO.setCustomerPassword(newPassword);
		count = customerDAO.customerUpdatePassword(customerVO);
		if (count == 1) {
			System.out.println("비밀번호변경이 완료되었습니다.");
		}
	}

	@Override
	public void customerUpdateName(CustomerVO customerVO) {
		int count = 0;
		System.out.println("이름을 변경합니다.");
		checkCustomerPassword(customerVO);

		String newCsutomerName;
		System.out.print("새 이름을 입력해주세요 > ");
		newCsutomerName = scanner.nextLine();
		customerVO.setCustomerName(newCsutomerName);
		count = customerDAO.customerUpdateName(customerVO);
		if (count == 1) {
			System.out.println("이름 변경이 완료되었습니다.");
		} else {
			System.out.println("이름 변경이 실패했습니다.");
		}
	}

	@Override
	public void customerUpdatePhoneNumber(CustomerVO customerVO) {
		int count = 0;
		System.out.println("전화번호를 변경합니다.");
		checkCustomerPassword(customerVO);
		int newCsutomerPhoneNumber;
		System.out.print("새 전화번호를 입력해주세요 > ");
		newCsutomerPhoneNumber = scanner.nextInt();
		scanner.nextLine();
		customerVO.setCustomerPhoneNumber(newCsutomerPhoneNumber);
		count = customerDAO.customerUpdatePhoneNumber(customerVO);
		if (count == 1) {
			System.out.println("전화번호 변경이 완료되었습니다.");
		} else {
			System.out.println("전화번호 변경이 실패했습니다.");
		}
	}

	@Override
	public void customerUpdateAddress(CustomerVO customerVO) {
		int count = 0;
		System.out.println("주소를 변경합니다.");
		checkCustomerPassword(customerVO);
		String newCsutomerAddress;
		System.out.print("새 주소를 입력해주세요 > ");
		newCsutomerAddress = scanner.nextLine();
		customerVO.setCustomerAddress(newCsutomerAddress);
		count = customerDAO.customerUpdateAddress(customerVO);
		if (count == 1) {
			System.out.println("주소 변경이 완료되었습니다.");
		} else {
			System.out.println("주소 변경이 실패했습니다.");
		}
	}

	@Override
	public void customerDelete(CustomerVO customerVO) { // delete 쿼리를 날리는게 아니라 delete-date 에 timestamp 삽입
		int count = 0;
		System.out.println("회원탈퇴를 진행하시겠습니까?");
		checkCustomerPassword(customerVO);
		count = customerDAO.customerDelete(customerVO);
		if (count == 1) {
			System.out.println("탈퇴처리가 완료되었습니다.");
		} else {
			System.out.println("탈퇴 처리가 실패되었습니다.");
		}
	}
}