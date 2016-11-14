package com.lukas;

public class Holiday {

	private int holidaysId;
	private String firstName;
	private String lastname;
	private String holidaySince;
	private String holidayUntil;
	private String status;
	private int daysOfHolidays;

	public Holiday(int holidaysId, String firstName, String lastname, String holidaySince, String holidayUntil,
			String status, int daysOfHolidays) {

		this.holidaysId = holidaysId;
		this.firstName = firstName;
		this.lastname = lastname;
		this.holidaySince = holidaySince;
		this.holidayUntil = holidayUntil;
		this.status = status;
		this.daysOfHolidays = daysOfHolidays;
	}

	public int getHolidaysId() {
		return holidaysId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public String getHolidaySince() {
		return holidaySince;
	}

	public String getHolidayUntil() {
		return holidayUntil;
	}

	public String getStatus() {
		return status;
	}

	public int getDaysOfHolidays() {
		return daysOfHolidays;
	}

}