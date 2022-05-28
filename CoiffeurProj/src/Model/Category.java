package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Category {

	private int id;
	private String name;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Category() {
	}

	public Category(int id, String name) {
		super();

		this.id = id;
		this.name = name;
	}

	public ArrayList<Category> getList() throws SQLException {
		ArrayList<Category> list = new ArrayList<>();

		Category obj;
		Connection con = conn.connDb();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM category");
			while (rs.next()) {
				obj = new Category();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
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
		public Category getFetch(int id) {
			Connection con = conn.connDb();
			Category c = new Category();
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM category WHERE id ="+id );
				while(rs.next()) {
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return c;
		}
		

	public boolean addCategory(String name) throws SQLException {

		String query = "INSERT INTO category" + "(name) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.connDb();

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
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

	public boolean deleteCategory(int id) throws SQLException {

		String query = "DELETE FROM category WHERE id = ? ";
		boolean key = false;
		Connection con = conn.connDb();
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

	public boolean updateCategory(int id ,String name) throws SQLException {

		String query = "UPDATE category SET name = ? WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);

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
	public ArrayList<User> getCategoryHairdresserList(int category_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
			Connection con = conn.connDb();

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
