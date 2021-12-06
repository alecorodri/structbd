package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.User;

public class UserService {
	
	public static String create_User(String nick, String pass) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_user(?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,nick);
			cs.setString(2,pass);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static void update_User(User u) throws SQLException{
		try{
			String sqlSentenc = "{call public.update_user(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,u.getIdUser());
			cs.setString(2,u.getName());
			cs.setString(3,u.getPassword());
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static void delete_User(int id_user) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_user(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_user);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<User> getUsers() throws SQLException{
		ArrayList<User> list = new ArrayList<User>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM users");
			while(rs.next()){
				User user = new User(rs.getInt("id_user"),rs.getString("user_name"),rs.getString("user_password"));
				list.add(user);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static User find_by_Name(String name) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM users WHERE users.user_name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("user_name"),rs.getString("user_password"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

	public static User find_by_Id(int id) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM users WHERE users.id_user = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("user_name"),rs.getString("user_password"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}
}
