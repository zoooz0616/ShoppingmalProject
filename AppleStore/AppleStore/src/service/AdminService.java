package src.service;

import src.dao.AdminDAO;
import src.dao.ProductDAO;
import src.vo.AdminVO;
import src.vo.ProductVO;
import java.util.Scanner;

public class AdminService implements IAdminService{
    Scanner scanner = new Scanner(System.in);
    AdminDAO adminDAO = new AdminDAO();

    @Override
    public AdminVO adminLogin() {
        scanner = new Scanner(System.in);
        System.out.print("관리자 ID > ");
        String adminId = scanner.nextLine();
        System.out.print("관리자 PW > ");
        String adminPassword = scanner.nextLine();
        int resultCount = adminDAO.selectAdmin(adminId, adminPassword);
        AdminVO adminVO = null;
        if (resultCount == 1) {
            System.out.println("관리자로 로그인 되었습니다.");
            adminVO = new AdminVO();
            adminVO.setAdminId(adminId);
            adminVO.setAdminPassword(adminPassword);
        } else {
            System.out.println("관리자 계정이 올바르지 않습니다.");
        }
        return adminVO;
    }
}