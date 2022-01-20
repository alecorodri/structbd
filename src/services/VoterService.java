<<<<<<< HEAD
package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.VoterDto;

public class VoterService {
	
	public static String createVoter(String name, Timestamp dateVot, String adrVoter, int cdr) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_voter(?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,name);
			cs.setTimestamp(2,dateVot);
			cs.setString(3,adrVoter);
			cs.setInt(4,cdr);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static String updateVoter(VoterDto voter) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.update(?,?,?,?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,voter.getNamVot());
			cs.setTimestamp(2,voter.getDateVot());
			cs.setString(3,voter.getAdrVot());
			cs.setInt(6,voter.getVote());
			cs.setString(4,voter.getCause());
			cs.setInt(5,voter.getCdr());
			cs.setInt(7,voter.getNumID());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static void deleteVoter(int idVoter) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_voter(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,idVoter);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<VoterDto> getVoters() throws SQLException{
		ArrayList<VoterDto> list = new ArrayList<VoterDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM voter");
			while(rs.next()){
				VoterDto voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getTimestamp("date_vot"), rs.getString("adr_voter"), rs.getInt("cdr"), rs.getInt("vote"), rs.getString("cause"));
				list.add(voter);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static VoterDto find_by_Name(String name) throws SQLException {
		VoterDto voter = null;
		try{
			String sqlSentenc = "SELECT * FROM voter WHERE voter.name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"),rs.getTimestamp("date_vot"), rs.getString("adr_voter"), rs.getInt("cdr"), rs.getInt("vote"), rs.getString("cause"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return voter;
	}
	
	public static VoterDto find_by_Id(int id) throws SQLException {
		VoterDto voter = null;
		try{
			String sqlSentenc = "SELECT * FROM voter WHERE voter.id_voter = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getTimestamp("date_vot"),rs.getString("adr_voter"), rs.getInt("cdr"), rs.getInt("vote"), rs.getString("cause"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return voter;
	}

}
=======
package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.VoterDto;

<<<<<<< HEAD
public class VoterService {
	
	public static String createVoter(String name, Timestamp dateVot, String adrVoter, int cdr) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_voter(?,?,?,?)}";
=======


public class VoterService {
	
	public static String createVoter(String name, Timestamp dateVot, String adrVoter, String cause, int cdr, boolean vote) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_voter(?,?,?,?,?,?)}";
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,name);
			cs.setTimestamp(2,dateVot);
			cs.setString(3,adrVoter);
<<<<<<< HEAD
			cs.setInt(4,cdr);
=======
			cs.setString(4,cause);
			cs.setInt(5,cdr);
			cs.setBoolean(5,vote);
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static String updateVoter(VoterDto voter) throws SQLException{
		String mistake = null;
		try{
<<<<<<< HEAD
			String sqlSentenc = "{call public.update(?,?,?,?,?,?,?)}";
=======
			String sqlSentenc = "{call public.update_voter(?,?,?,?,?,?,?)}";
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,voter.getNamVot());
			cs.setTimestamp(2,voter.getDateVot());
			cs.setString(3,voter.getAdrVot());
<<<<<<< HEAD
			cs.setInt(6,voter.getVote());
			cs.setString(4,voter.getCause());
			cs.setInt(5,voter.getCdr());
			cs.setInt(7,voter.getNumID());
=======
			cs.setBoolean(4,voter.isVote());
			cs.setString(5,voter.getCause());
			cs.setInt(6,voter.getCdr());
			cs.setInt(8,voter.getNumID());
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static void deleteVoter(int idVoter) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_voter(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,idVoter);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<VoterDto> getVoters() throws SQLException{
		ArrayList<VoterDto> list = new ArrayList<VoterDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM voter");
			while(rs.next()){
<<<<<<< HEAD
				VoterDto voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getTimestamp("date_vot"), rs.getString("adr_voter"), rs.getInt("cdr"), rs.getInt("vote"), rs.getString("cause"));
=======
				VoterDto voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getString("adr_vot"), rs.getTimestamp("date_vot"), rs.getInt("cdr"), rs.getBoolean("vote"), rs.getString("cause"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
				list.add(voter);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static VoterDto find_by_Name(String name) throws SQLException {
		VoterDto voter = null;
		try{
			String sqlSentenc = "SELECT * FROM voter WHERE voter.name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
<<<<<<< HEAD
				voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"),rs.getTimestamp("date_vot"), rs.getString("adr_voter"), rs.getInt("cdr"), rs.getInt("vote"), rs.getString("cause"));
=======
				voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getString("adr_vot"), rs.getTimestamp("date_vot"), rs.getInt("cdr"), rs.getBoolean("vote"), rs.getString("cause"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return voter;
	}
	
	public static VoterDto find_by_Id(int id) throws SQLException {
		VoterDto voter = null;
		try{
			String sqlSentenc = "SELECT * FROM voter WHERE voter.id_voter = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
<<<<<<< HEAD
				voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getTimestamp("date_vot"),rs.getString("adr_voter"), rs.getInt("cdr"), rs.getInt("vote"), rs.getString("cause"));
=======
				voter = new VoterDto(rs.getInt("id_voter"), rs.getString("name"), rs.getString("adr_vot"), rs.getTimestamp("date_vot"), rs.getInt("cdr"), rs.getBoolean("vote"), rs.getString("cause"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return voter;
	}

}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
