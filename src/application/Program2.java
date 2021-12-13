package application;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== - TESTE 01: FindById - ===");
		Department department = departmentDao.findById(3);
		System.out.println(department);

		System.out.println("\n=== - TESTE 02: FindAll - ===");
		String organizacao = "Name";
		List<Department> listAll = departmentDao.findAll(organizacao);

		for (Department x : listAll) {
			System.out.println(x);
		}

		System.out.println("\n=== - TESTE 03: Insert - ===");
		Department DepartmentInsert = new Department(null, "Clothes");
		departmentDao.insert(DepartmentInsert);
		System.out.println("Inserido! novo id: " + DepartmentInsert.getId());

		System.out.println("\n=== - TESTE 04: Update - ===");
		department = departmentDao.findById(9);
		System.out.println(department);

		department.setName("Shoes");
		departmentDao.update(department);

		System.out.println(department);

		System.out.println("\n=== - TESTE 05: Delete - ===");
		int id = 9;
		departmentDao.deleteById(id);

		System.out.println("O vendedor id: " + id + " Foi excluido com sucesso!");

		DB.closeConnection();
		sc.close();
	}
}
