package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Electoral_ProcessDto;

public class ElectoralProcessService {
	
<<<<<<< HEAD
	public static String create_EP(int idMunicipality, int roundNum) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.create_electoral_process(?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,idMunicipality);
			cs.setInt(2,1);
=======
	public static String create_EP(int roundNum, int id_EProcess, int idMunicipality) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.create_EP(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,roundNum);
			cs.setInt(2,id_EProcess);
			cs.setInt(3,idMunicipality);
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
			String sqlSentenc = "{call public.update_electoral_process(?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(2,ep.getRoundNum());
			cs.setInt(1,ep.getId_EProcess());
=======
			String sqlSentenc = "{call public.update_EP(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,ep.getRoundNum());
			cs.setInt(2,ep.getId_EProcess());
			cs.setInt(3,ep.getIdMunicipality());
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
        return mistake;
	}
	
	public static void delete_EP(int id_EProcess) throws SQLException{
		try{
<<<<<<< HEAD
			String sqlSentenc = "{call public.delete_electoral_process(?)}";
=======
			String sqlSentenc = "{call public.delete_EP(?)}";
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
				Electoral_ProcessDto ep = new Electoral_ProcessDto(rs.getInt("roundnum"),rs.getInt("id_electoral_process"),rs.getInt("municipality"));
=======
				Electoral_ProcessDto ep = new Electoral_ProcessDto(rs.getInt("roundNum"),rs.getInt("id_EProcess"),rs.getInt("idMunicipality"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
				list.add(ep);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
<<<<<<< HEAD
	public static Electoral_ProcessDto find_by_Municipality(int id_Mun) throws SQLException {
		Electoral_ProcessDto ep = null;
		try{
			String sqlSentenc = "SELECT * FROM electoral_process WHERE electoral_process.municipality = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_Mun);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				ep = new Electoral_ProcessDto(rs.getInt("roundnum"),rs.getInt("id_electoral_process"),rs.getInt("municipality"));
=======
	public static Electoral_ProcessDto find_by_Municipality(String id_Mun) throws SQLException {
		Electoral_ProcessDto ep = null;
		try{
			String sqlSentenc = "SELECT * FROM electoral_process WHERE electoral_process.id_municipality = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, id_Mun);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				ep = new Electoral_ProcessDto(rs.getInt("roundNum"),rs.getInt("id_EProcess"),rs.getInt("idMunicipality"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
				ep = new Electoral_ProcessDto(rs.getInt("roundnum"),rs.getInt("id_electoral_process"),rs.getInt("municipality"));
=======
				ep = new Electoral_ProcessDto(rs.getInt("roundNum"),rs.getInt("id_EProcess"),rs.getInt("idMunicipality"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return ep;
	}
}
<<<<<<< HEAD

=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
