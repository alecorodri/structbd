package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.DistrictDto;

public class DistrictService {
	
	public static String create_district(String name, int mcp) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_district(?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			cs.setInt(2, mcp);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}

	public static String update_district(DistrictDto dist) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.update_district(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,dist.getCodDis());
			cs.setInt(2,dist.getIdMunicipality());
			cs.setString(3,dist.getNamDis());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static void delete_district(int id_district) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_district(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_district);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<DistrictDto> getDistrict() throws SQLException{
		ArrayList<DistrictDto> list = new ArrayList<DistrictDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM district");
			while(rs.next()){
				DistrictDto dist = new DistrictDto(rs.getInt("id_district"), rs.getString("name_district"), rs.getInt("municipality"));
				list.add(dist);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static DistrictDto find_by_Name(String name) throws SQLException {
		DistrictDto dist = null;
		try{
			String sqlSentenc = "SELECT * FROM district WHERE district.name_district = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				dist = new DistrictDto(rs.getInt("id_district"), rs.getString("name_district"), rs.getInt("municipality"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return dist;
	}
	
	public static DistrictDto find_by_Id(int id) throws SQLException {
		DistrictDto dist = null;
		try{
			String sqlSentenc = "SELECT * FROM district WHERE district.id_district = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				dist = new DistrictDto(rs.getInt("id_district"), rs.getString("name_district"), rs.getInt("municipality"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return dist;
	}
}
