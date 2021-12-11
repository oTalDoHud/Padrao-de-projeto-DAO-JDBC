package application;



import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		Department depar = new Department(1, "Books");
		
		Seller seller = new Seller(21, "Maria", "Maria@gmail.com", new Date(), 3000.0, depar);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(seller);
	
	}
}
