package com.lukas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	String url = "jdbc:h2:~/employees";
	String user = "sa";
	String pass = "";

	public Employee getEmployee(int empId) {
		Employee employee = null;
		Manager manager = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "SELECT FIRSTNAME, LASTNAME, POSITION FROM EMPLOYEES WHERE EMP_ID=?";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, empId);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				employee = new Employee(empId, rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
						rs.getString("POSITION"));
				return employee;

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean isManager(int empId) {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String sql = "SELECT EMP_ID FROM EMPLOYEES WHERE ? = MANAGER_EMP_ID";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, empId);
			preparedStatement.execute();
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException();
	}
}