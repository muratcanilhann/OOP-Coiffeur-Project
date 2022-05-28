package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id,hairdresserID,customerID;
	private String hairdresserName,customerName,appDate;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Appointment(int id, int hairdresserID, int customerID, String hairdresserName, String customerName,
			String appDate) {
		super();
		this.id = id;
		this.hairdresserID = hairdresserID;
		this.customerID = customerID;
		this.hairdresserName = hairdresserName;
		this.customerName = customerName;
		this.appDate = appDate;
	}

	public Appointment() {
	}
		public ArrayList<Appointment> getCustomerList(int customer_id) throws SQLException {
			ArrayList<Appointment> list = new ArrayList<>();

			Appointment obj;
			Connection con = conn.connDb();

			try {

				st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM appointment WHERE customer_id = " + customer_id);
				
				while (rs.next()) {
					obj = new Appointment();
					obj.setId(rs.getInt("id"));
					obj.setCustomerID(rs.getInt("hairdresser_id"));
					obj.setHairdresserName(rs.getString("hairdresser_name"));
					obj.setCustomerID(rs.getInt("customer_id"));
					obj.setCustomerName(rs.getString("customer_name"));
					obj.setAppDate(rs.getString("app_date"));
					list.add(obj);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st.close();
				rs.close();
				con.close();
			}

			return list;

		}
		
		public ArrayList<Appointment> getHairdresserList(int hairdresser_id) throws SQLException {
			ArrayList<Appointment> list = new ArrayList<>();

			Appointment obj;
			Connection con = conn.connDb();

			try {

				st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM appointment WHERE hairdresser_id = " + hairdresser_id);
			
				while (rs.next()) {
					obj = new Appointment();
					obj.setId(rs.getInt("id"));
					obj.setCustomerID(rs.getInt("hairdresser_id"));
					obj.setHairdresserName(rs.getString("hairdresser_name"));
					obj.setCustomerID(rs.getInt("customer_id"));
					obj.setCustomerName(rs.getString("customer_name"));
					obj.setAppDate(rs.getString("app_date"));
					list.add(obj);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st.close();
				rs.close();
				con.close();
			}

			return list;

		}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHairdresserID() {
		return hairdresserID;
	}

	public void setHairdresserID(int hairdresserID) {
		this.hairdresserID = hairdresserID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getHairdresserName() {
		return hairdresserName;
	}

	public void setHairdresserName(String hairdresserName) {
		this.hairdresserName = hairdresserName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
}
