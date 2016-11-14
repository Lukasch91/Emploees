package com.lukas;

import java.util.List;

public class Manager extends Employee {

	HolidayDAO holidayDAO = new HolidayDAO();

	public Manager(int emplId, String firstName, String lastName, String position) {
		super(emplId, firstName, lastName, position);
	}

	public List<Holiday> getRequests() {
		return holidayDAO.getRequests();

	}

	public void approveHoliday(Holiday holiday) {
		
		holidayDAO.approveHoliday(holiday);
		
	}

	public void rejectHoliday() {

	}

}
