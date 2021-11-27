package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NominatedDto;

public class NominatedService {
	
	public static String create_nominated(String ocupNomi, String profeNomi, String phoneNomi, String intgRNomi, String biogDataNomi, int idNominated, int idVoter, int processE, int cantVotes) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.create_nominated(?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,ocupNomi);
			cs.setString(2,profeNomi);
			cs.setString(3,phoneNomi);
			cs.setString(4,intgRNomi);
			cs.setString(5,biogDataNomi);
			cs.setInt(6,idNominated);
			cs.setInt(7,idVoter);
			cs.setInt(8,processE);
			cs.setInt(9,cantVotes);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
        return mistake;
	}
	
	public static String update_nominated(NominatedDto nom) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.update_nominated(?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,nom.getOcupNomi());
			cs.setString(2,nom.getProfeNomi());
			cs.setString(3,nom.getPhoneNomi());
			cs.setString(4,nom.getIntgRNomi());
			cs.setString(5,nom.getBiogDataNomi());
			cs.setInt(6,nom.getIdNominated());
			cs.setInt(7,nom.getIdVoter());
			cs.setInt(8,nom.getProcessE());
			cs.setInt(9,nom.getCantVotes());
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
        return mistake;
	}
	
	public static void delete_nominated(int id_nominated) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_nominated(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_nominated);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<NominatedDto> getNominatedDto() throws SQLException{
		ArrayList<NominatedDto> list = new ArrayList<NominatedDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM nominated");
			while(rs.next()){
				NominatedDto nom = new NominatedDto(rs.getString("ocupNomi"), rs.getString("profeNomi"), rs.getString("phoneNomi"), rs.getString("intgRNomi"), rs.getString("biogDataNomi"), rs.getInt("idNominated"), rs.getInt("idVoter"), rs.getInt("processE"), rs.getInt("cantVotes"));
				list.add(nom);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public static NominatedDto find_by_Id(int id) throws SQLException {
		NominatedDto nom = null;
		try{
			String sqlSentenc = "SELECT * FROM nominated WHERE nominated.id_nominated = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				nom = new NominatedDto(rs.getString("ocupNomi"), rs.getString("profeNomi"), rs.getString("phoneNomi"), rs.getString("intgRNomi"), rs.getString("biogDataNomi"), rs.getInt("idNominated"), rs.getInt("idVoter"), rs.getInt("processE"), rs.getInt("cantVotes"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return nom;
	}
}