package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Customer extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Customer() {

	}

	public Customer(int id, String identitynum, String name, String type, String password) {
		super(id, identitynum, name, type, password);
	}

	public boolean register(String identitynum, String password, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(identitynum,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE identitynum = '" + identitynum + "'");
			while (rs.next()) {

				duplicate = true;
				Helper.showMsg("This identity number There is another record for this identity number.");
				break;

			}
			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, identitynum);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "customer");
				preparedStatement.executeUpdate();
				key = 1;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;

		else
			return false;

	}

	public boolean addAppointment(int hairdresser_id, int customer_id, String hairdresser_name, String customer_name,
			String app_date) throws SQLException {
		int key = 0;
		String query = "INSERT INTO appointment" + "(hairdresser_id,hairdresser_name,customer_id,customer_name,app_date) VALUES" + "(?,?,?,?,?)";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, hairdresser_id);
			preparedStatement.setString(2, hairdresser_name);
			preparedStatement.setInt(3, customer_id);
			preparedStatement.setString(4, customer_name);
			preparedStatement.setString(5, app_date);

			preparedStatement.executeUpdate();
			key = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;

		else
			return false;

	}
	
	public boolean updateWhourStatus(int hairdresser_id, String wdate) throws SQLException {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE hairdresser_id = ? AND wdate = ?";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, hairdresser_id);
			preparedStatement.setString(3, wdate);
			
			preparedStatement.executeUpdate();
			key = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;

		else
			return false;

	}


}
