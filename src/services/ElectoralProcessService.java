package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Electoral_ProcessDto;

public class ElectoralProcessService {
	
	public static String create_EP(int roundNum, int id_EProcess, int idMunicipality) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.create_EP(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,roundNum);
			cs.setInt(2,id_EProcess);
			cs.setInt(3,idMunicipality);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
        return mistake;
	}
	
	public static String update_EP(Electoral_ProcessDto ep) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.update_EP(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,ep.getRoundNum());
			cs.setInt(2,ep.getId_EProcess());
			cs.setInt(3,ep.getIdMunicipality());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
        return mistake;
	}
	
	public static void delete_EP(int id_EProcess) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_EP(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_EProcess);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<Electoral_ProcessDto> getElectoral_ProcessDto() throws SQLException{
		ArrayList<Electoral_ProcessDto> list = new ArrayList<Electoral_ProcessDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM electoral_process");
			while(rs.next()){
				Electoral_ProcessDto ep = new Electoral_ProcessDto(rs.getInt("roundNum"),rs.getInt("id_EProcess"),rs.getInt("idMunicipality"));
				list.add(ep);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static Electoral_ProcessDto find_by_Municipality(String id_Mun) throws SQLException {
		Electoral_ProcessDto ep = null;
		try{
			String sqlSentenc = "SELECT * FROM electoral_process WHERE electoral_process.id_municipality = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, id_Mun);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				ep = new Electoral_ProcessDto(rs.getInt("roundNum"),rs.getInt("id_EProcess"),rs.getInt("idMunicipality"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return ep;
	}

	public static Electoral_ProcessDto find_by_Id(int id) throws SQLException {
		Electoral_ProcessDto ep = null;
		try{
			String sqlSentenc = "SELECT * FROM electoral_process WHERE electoral_process.id_electoral_process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				ep = new Electoral_ProcessDto(rs.getInt("roundNum"),rs.getInt("id_EProcess"),rs.getInt("idMunicipality"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return ep;
	}
}
