package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Whour {
	private int id,hairdresser_id;
	private String hairdresser_name,wdate,status;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Whour(int id, int hairdresser_id, String hairdresser_name, String wdate, String status) {
		
		this.id = id;
		this.hairdresser_id = hairdresser_id;
		this.hairdresser_name = hairdresser_name;
		this.wdate = wdate;
		this.status = status;
	}
	public Whour() {
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHairdresser_id() {
		return hairdresser_id;
	}

	public void setHairdresser_id(int hairdresser_id) {
		this.hairdresser_id = hairdresser_id;
	}

	public String getHairdresser_name() {
		return hairdresser_name;
	}

	public void setHairdresser_name(String hairdresser_name) {
		this.hairdresser_name = hairdresser_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<Whour> getWhourList(int hairdresser_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();

		Whour obj;
		try {
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status ='a' AND hairdresser_id = " + hairdresser_id);
			while (rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setHairdresser_id(rs.getInt("hairdresser_id"));
				obj.setHairdresser_name(rs.getString("hairdresser_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
		
	
	
}
