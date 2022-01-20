<<<<<<< HEAD
package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MunicipalityDto;

public class MunicipalityService {

	public static String create_Municipality(String name) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_municipality(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,name);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static String update_Municipality(MunicipalityDto municipality) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.update_municipality(?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,municipality.getCodMun());
			cs.setString(2,municipality.getNamMun());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
	    }
		return mistake;
	}
	
	public static void delete_municipality(int id_municipality) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_municipality(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_municipality);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<MunicipalityDto> getMunicipality() throws SQLException{
		ArrayList<MunicipalityDto> list = new ArrayList<MunicipalityDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM municipality");
			while(rs.next()){
				MunicipalityDto mcp = new MunicipalityDto(rs.getInt("id_municipality"), rs.getString("name"));
				list.add(mcp);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static MunicipalityDto find_by_Name(String name) throws SQLException {
		MunicipalityDto mcp = null;
		try{
			String sqlSentenc = "SELECT * FROM municipality WHERE municipality.name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				mcp = new MunicipalityDto(rs.getInt("id_municipality"),rs.getString("name"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return mcp;
	}
	
	public static MunicipalityDto find_by_Id(int id) throws SQLException {
		MunicipalityDto mcp = null;
		try{
			String sqlSentenc = "SELECT * FROM municipality WHERE municipality.id_municipality = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				mcp = new MunicipalityDto(rs.getInt("id_municipality"),rs.getString("name"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return mcp;
	}
}
=======
package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MunicipalityDto;

public class MunicipalityService {

	public static String create_Municipality(String name) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_municipality(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,name);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static String update_Municipality(MunicipalityDto municipality) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.update_municipality(?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,municipality.getCodMun());
			cs.setString(2,municipality.getNamMun());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
	    }
		return mistake;
	}
	
	public static void delete_municipality(int id_municipality) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_municipality(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_municipality);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<MunicipalityDto> getMunicipality() throws SQLException{
		ArrayList<MunicipalityDto> list = new ArrayList<MunicipalityDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM municipality");
			while(rs.next()){
				MunicipalityDto mcp = new MunicipalityDto(rs.getInt("id_municipality"), rs.getString("name"));
				list.add(mcp);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static MunicipalityDto find_by_Name(String name) throws SQLException {
		MunicipalityDto mcp = null;
		try{
			String sqlSentenc = "SELECT * FROM municipality WHERE municipality.name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				mcp = new MunicipalityDto(rs.getInt("id_municipality"),rs.getString("name"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return mcp;
	}
	
	public static MunicipalityDto find_by_Id(int id) throws SQLException {
		MunicipalityDto mcp = null;
		try{
			String sqlSentenc = "SELECT * FROM municipality WHERE municipality.id_municipality = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				mcp = new MunicipalityDto(rs.getInt("id_municipality"),rs.getString("name"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return mcp;
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
