package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== - TESTE 01: FindById - ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== - TESTE 02: FindByDepartment - ===");
		Integer departmentId = 2;
		List<Seller> list = sellerDao.findByDepartment(departmentId);
		
		for (Seller x: list) {
			System.out.println(x);
		}
	}
}
