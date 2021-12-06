package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NominatedDto;

public class NominatedService {
	
	public static String create_nominated(String ocupNomi, String profeNomi, String phoneNomi, String intgRNomi, String biogDataNomi, int idVoter, int processE, int cantVotes) throws SQLException{
		String mistake = null;
        try{
			String sqlSentenc = "{call public.create_nominated(?,?,?,?,?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(2,ocupNomi);
			cs.setString(3,profeNomi);
			cs.setString(4,phoneNomi);
			cs.setString(5,intgRNomi);
			cs.setString(6,biogDataNomi);
			cs.setInt(1,idVoter);
			cs.setInt(7,processE);
			cs.setInt(8,cantVotes);
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
			cs.setString(3,nom.getOcupNomi());
			cs.setString(4,nom.getProfeNomi());
			cs.setString(5,nom.getPhoneNomi());
			cs.setString(6,nom.getIntgRNomi());
			cs.setString(9,nom.getBiogDataNomi());
			cs.setInt(1,nom.getIdNominated());
			cs.setInt(2,nom.getIdVoter());
			cs.setInt(7,nom.getProcessE());
			cs.setInt(8,nom.getCantVotes());
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
				NominatedDto nom = new NominatedDto(rs.getString("ocupation"), rs.getString("profetion"), rs.getString("phone"), rs.getString("int_rev"), rs.getString("bio_data"), rs.getInt("id_nominated"), rs.getInt("id_voter"), rs.getInt("process_e"), rs.getInt("cant_vote"));
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
				nom = new NominatedDto(rs.getString("ocupation"), rs.getString("profetion"), rs.getString("phone"), rs.getString("int_rev"), rs.getString("bio_data"), rs.getInt("id_nominated"), rs.getInt("id_voter"), rs.getInt("process_e"), rs.getInt("cant_vote"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return nom;
	}
}