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