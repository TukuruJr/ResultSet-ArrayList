

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Users;

public class Main {
	
	private Connection cn;

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		new Main();

	}
	
	public Main() {
		// TODO Auto-generated constructor stub
		try {
			GetConnection();
			Render();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			 System.out.print(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 System.out.print(e.getMessage());
		}
	
	}

	//a method to Establish connection.
	private void GetConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); //register driver
		cn = DriverManager.getConnection("jdbc:mysql://localhost/MVC","root",""); //establish connection and connect to MVC database.
	}
	
	
	//fetch a list of Users
	private ArrayList<Users> getListUsers() throws SQLException{
		ArrayList<Users> users = new ArrayList<>();
		PreparedStatement pst = cn.prepareStatement("select * from users order by name");
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			
			//fetch data and populate Users object then add to the list
			String name = rs.getString(2);
			String email = rs.getString(3);
			String pass = rs.getString(4);
			
			
			users.add(new Users(name, email, pass));
		}
		return users;
	}
	
	
	//show data
	private void Render() throws SQLException {
		ArrayList<Users> users =getListUsers();
		 users.forEach(user->{
			System.out.println(user.toString());
		});
	}
}
