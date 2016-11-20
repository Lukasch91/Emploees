package com.lukas;

import java.util.List;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {

		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee;
		Manager manager;

		Scanner reader = new Scanner(System.in);
		boolean keepRunning = true;
		while (keepRunning) {

			System.out.println("Enter employee's ID");
			int input = reader.nextInt();

			if (employeeDAO.isManager(input)) {
				manager = (Manager) employeeDAO.getEmployee(input);
				System.out.println("Welcome you are" + manager.getEmplId() + " " + manager.getFirstName() + " "
						+ manager.getLastName() + " " + manager.getPosition());

			} else {
				employee = employeeDAO.getEmployee(input);
				System.out.println("Welcome, you are " + employee.getEmplId() + " " + employee.getFirstName() + " "
						+ employee.getLastName() + " " + employee.getPosition());
				System.out.println("Choose : /n 1 - Request holiday /n 2 - Get my holiday plan");
				String operation = reader.next();
				if (operation.equals("1")) {
					System.out.println("Input since : ");
					String since = reader.next();
					System.out.println("Input until : ");
					String until = reader.next();
					employee.requestHoliday(employee, since, until);
				} else if (operation.equals("2")) {
				}
			}
		}
	}
}