package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	
	void insert (Department depar);
	
	void update (Department depar);
	
	void deleteById (Integer id);
	
	Department findById (Integer id);
	
	List<Department> findAll(String organizacao);
	
}
