package com.lukas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HolidayDAO {

	String url = "jdbc:h2:~/employees";
	String user = "sa";
	String pass = "";

	public int getNewId() {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "SELECT MAX(HOL_ID) AS RESULT FROM EMP_HOL";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				return rs.getInt("RESULT") + 1;
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	public int getNumberOfRequests() {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "SELECT COUNT(ID) AS RESULT FROM HOLIDAY_REQUESTS WHERE STATUS IS NULL";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				return rs.getInt("RESULT");
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	public List<Holiday> getRequests() {

		List<Holiday> requestList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "SELECT HOLIDAY_REQUESTS.ID, EMPLOYEES.FIRSTNAME, EMPLOYEES.LASTNAME, "
					+ "HOLIDAY_REQUESTS.SINCE, HOLIDAY_REQUESTS.UNTIL, HOLIDAY_REQUESTS.STATUS, HOLIDAY_REQUESTS.UNTIL-HOLIDAY_REQUESTS.SINCE AS DAYS"
					+ " FROM EMPLOYEES LEFT OUTER JOIN EMP_HOL ON EMPLOYEES.EMP_ID =EMP_HOL.EMP_ID "
					+ "LEFT OUTER JOIN HOLIDAY_REQUESTS ON EMP_HOL.HOL_ID=HOLIDAY_REQUESTS.ID;";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Holiday holiday = new Holiday(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
							rs.getString("SINCE"), rs.getString("Until"), rs.getString("STATUS"), rs.getInt("DAYS"));
					requestList.add(holiday);
				}
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return requestList;
	}

	public void addRequest(Employee employee, String holidaySince, String holidayUntil) {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			conn.setAutoCommit(false);

			String sql1 = "INSERT INTO EMP_HOL (emp_id) values (?);";
			String sql2 = "INSERT INTO HOLIDAY_REQUESTS VALUES (?, ?, ?, ?, null)";

			PreparedStatement preparedStatement = conn.prepareStatement(sql1);
			PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);

			preparedStatement.setInt(1, employee.getEmplId());
			preparedStatement.execute();
			preparedStatement2.setLong(1, getNewId());
			preparedStatement2.setInt(2, employee.getEmplId());
			preparedStatement2.setString(3, holidaySince);
			preparedStatement2.setString(4, holidayUntil);
			preparedStatement2.execute();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void approveHoliday(Holiday holiday) {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String sql = "UPDATE HOLIDAY_REQUESTS SET STATUS='A' WHERE ID=?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, holiday.getHolidaysId());
			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
