package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department objDepartment) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO department (Name) "
				+ "VALUES (?);";
		
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, objDepartment.getName());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas > 0) {
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					objDepartment.setId(id);
				}
			}
			else {
				throw new DbException("Erro inesperado. Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void update(Department objDepartment) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE department SET Name = ? "
				+ "WHERE Id = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, objDepartment.getName());
			stmt.setInt(2, objDepartment.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas == 0) {
				throw new DbException("Erro inesperado. Nenhuma linha afetada");
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM department "
				+ "WHERE Id = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas == 0) {
				throw new DbException("Erro inesperado! Nenhuma linha alterada");
			}
				
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("SELECT Id, name "
					+ "FROM department "
					+ "WHERE id = ?;");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
					
				return dep;
			}
			
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll(String organizacao) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("SELECT Id, Name "
					+ "FROM department "
					+ "ORDER BY ?;");
			
			stmt.setString(1, organizacao);
			
			rs = stmt.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Department dep = instantiateDepartment(rs);
				
				list.add(dep);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}
	}
}
