package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	
void insert (Seller depar);
	
	void update (Seller depar);
	
	void deleteById (Integer id);
	
	Seller findById (Integer id);
	
	List<Seller> findAll ();
	
	List<Seller> findByDepartment (Integer id);

}
