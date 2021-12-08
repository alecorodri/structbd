package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Electoral_CollegeDto;

public class CollegeService {

	public static String createCollege(String name, String adr, int dist) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_college(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,name);
			cs.setString(2,adr);
			cs.setInt(3,dist);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static String updateCollege(Electoral_CollegeDto college) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.update_college(?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,college.getCodCollege());
			cs.setInt(2,college.getId_district());
			cs.setString(3,college.getNameCollege());
			cs.setString(4,college.getAdress());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static void deleteCollege(int idCollege) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_college(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,idCollege);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<Electoral_CollegeDto> getVoters() throws SQLException{
		ArrayList<Electoral_CollegeDto> list = new ArrayList<Electoral_CollegeDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM college");
			while(rs.next()){
				Electoral_CollegeDto college = new Electoral_CollegeDto(rs.getInt("id_college"), rs.getString("name_college"), rs.getString("address"), rs.getInt("district"));
				list.add(college);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static Electoral_CollegeDto find_by_Name(String name) throws SQLException {
		Electoral_CollegeDto college = null;
		try{
			String sqlSentenc = "SELECT * FROM college WHERE college.name_college = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				college = new Electoral_CollegeDto(rs.getInt("id_college"), rs.getString("name_college"), rs.getString("address"), rs.getInt("district"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return college;
	}
	
	public static Electoral_CollegeDto find_by_Id(int id) throws SQLException {
		Electoral_CollegeDto college = null;
		try{
			String sqlSentenc = "SELECT * FROM college WHERE college.id_college = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				college = new Electoral_CollegeDto(rs.getInt("id_college"), rs.getString("name_college"), rs.getString("address"), rs.getInt("district"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return college;
	}
}
