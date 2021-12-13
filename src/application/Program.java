package application;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== - TESTE 01: FindById - ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== - TESTE 02: FindByDepartment - ===");
		Integer departmentId = 2;
		List<Seller> listDepartment = sellerDao.findByDepartment(departmentId);
		
		for (Seller x: listDepartment) {
			System.out.println(x);
		}
		
		System.out.println("\n=== - TESTE 03: FindAll - ===");
		List<Seller> listAll = sellerDao.findAll("Name");
		
		for (Seller x: listAll) {
			System.out.println(x);
		}
		
		
		/*
		 * System.out.println("\n=== - TESTE 04: Insert - ==="); Seller sellerInsert =
		 * new Seller(null, "Greg", "Greg@gmail.com" , new Date(), 4000.00, new
		 * Department(2, null)); sellerDao.insert(sellerInsert);
		 * System.out.println("Inserido! novo id: " + sellerInsert.getId());
		 */
		
		
		System.out.println("\n=== - TESTE 05: Update - ===");
		seller = sellerDao.findById(1);
		System.out.println(seller);
		
		seller.setName("Martha Waine");
		sellerDao.update(seller);
		
		System.out.println(seller);
		
		System.out.println("\n=== - TESTE 06: Delete - ===");
		int id = 10;
		sellerDao.deleteById(id);
		System.out.println("O vendedor id: " + id + "Foi excluido com sucesso!");
		DB.closeConnection();
		sc.close();
	}
}
