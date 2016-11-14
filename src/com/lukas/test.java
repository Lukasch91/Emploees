package com.lukas;

import java.util.List;

public class test {

	public static void main(String[] args) {

		Employee employee = new Employee(1, "Lukas", "Cholomskis", "Direktorius");
		employee.requestHoliday(employee, "2016-01-01", "2016-02-01");

		Manager manager = new Manager(2, "Tadas", "Petrauskas", "Manager");
		manager.requestHoliday(manager, "2016-02-01", "2016-04-03");

		System.out.println(employee.holidayRequestDAO.getNumberOfRequests());

		List<Holiday> holidayList = manager.getRequests();

		for (Holiday holiday : holidayList) {
			System.out.println(holiday.getHolidaysId() + " " + holiday.getFirstName() + " " + holiday.getLastname()
					+ " " + holiday.getHolidaySince() + " " + holiday.getHolidayUntil() + " " + holiday.getStatus()
					+ " " + holiday.getDaysOfHolidays());
		}

		Holiday holiday = holidayList.get(0);
		manager.approveHoliday(holiday);
	}
}
