package com.lukas;

public class Employee {

	private int emplId;
	private String firstName;
	private String lastName;
	private String position;

	public Employee(int emplId, String firstName, String lastName, String position) {
		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;

	}
	
	HolidayDAO holidayRequestDAO = new HolidayDAO();

	public int getEmplId() {
		return emplId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPosition() {
		return position;
	}

	public void requestHoliday(Employee employee, String holidaySince, String holidayUntil) {
		
		holidayRequestDAO.addRequest(employee, holidaySince, holidayUntil);
		
		
	}
}
