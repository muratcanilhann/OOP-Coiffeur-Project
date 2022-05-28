package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Staff extends User {
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Staff(int id, String identitynum, String name, String type, String password) {
		super(id, identitynum, name, type, password);
	}

	public Staff() {
	}

	public ArrayList<User> getHairdresserList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'Hairdresser' ");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("identitynum"), rs.getString("name"), rs.getString("type"),
						rs.getString("password"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public ArrayList<User> getCategoryHairdresserList(int category_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id,u.identitynum,u.password ,u.type,u.name FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE category_id = " +category_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.identitynum"), rs.getString("u.name"), rs.getString("u.type"),
						rs.getString("password"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	

	public boolean addHairdresser(String identitynum, String password, String name) throws SQLException {

		String query = "INSERT INTO user" + "(identitynum, password , name, type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, identitynum);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "Hairdresser");
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

	public boolean deleteHairdresser(int id) throws SQLException {

		String query = "DELETE FROM user WHERE id = ? ";
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

	public boolean updateHairdresser(int id, String identitynum, String password, String name) throws SQLException {

		String query = "UPDATE user SET name = ? , identitynum = ? ,password = ? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, identitynum);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);

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
	public boolean addWorker(int user_id , int category_id) throws SQLException {

		String query = "INSERT INTO worker" + "(user_id,category_id) VALUES" + "(?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE category_id =" +category_id+" AND user_id="+ user_id);
			while(rs.next()) {
				count++;
			}
			if(count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, category_id);
				preparedStatement.executeUpdate();	
			}
			
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