package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hairdresser extends User {
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Hairdresser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hairdresser(int id, String identitynum, String name, String type, String password) {
		super(id, identitynum, name, type, password);
	}

	public boolean addWhour(int haidresser_id, String hairdresser_name, String wdate) throws SQLException {
		int key = 0;
		int count = 0;
		String query = "INSERT INTO whour" + "(hairdresser_id,hairdresser_name,wdate) VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND 	hairdresser_id= " + haidresser_id + " AND wdate ='" + wdate + "'");
			while(rs.next()) {
				count++;
				break;
				
				
			}
			if(count==0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, haidresser_id);
				preparedStatement.setString(2, hairdresser_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
				
			}
			key = 1;
		
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key == 1)
			return true;	
			
		else 	
		return false;

	}
	public ArrayList<Whour> getWhourList(int hairdresser_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();

		Whour obj;
		try {
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
	public boolean deleteWhour(int id) throws SQLException {

		String query = "DELETE FROM whour WHERE id = ? ";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();

		}

		if (key)
			return true;
		else
			return false;

	}
	
}
