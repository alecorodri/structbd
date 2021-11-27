package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CDRDto;

public class CdrService {
	
	public static String create_CDR(int codCDR, String namCDR, int id_presidentCDR, int id_college) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_cdr(?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,codCDR);
			cs.setString(2,namCDR);
			cs.setInt(3,id_presidentCDR);
			cs.setInt(4,id_college);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static void update_CDR(CDRDto c) throws SQLException{
		try{
			String sqlSentenc = "{call public.update_cdr(?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,c.getCodCDR());
			cs.setString(2,c.getNamCDR());
			cs.setInt(3,c.getId_presidentCDR());
			cs.setInt(4,c.getId_college());
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static void delete_CDR(int id_cdr) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_cdr(?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,id_cdr);
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static ArrayList<CDRDto> getCDRDto() throws SQLException{
		ArrayList<CDRDto> list = new ArrayList<CDRDto>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM cdrTo");
			while(rs.next()){
				CDRDto cdr = new CDRDto(rs.getInt("codCDR"),rs.getString("namCDR"),rs.getInt("id_presidentCDR"),rs.getInt("id_college"));
				list.add(cdr);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static CDRDto find_by_Name(String name) throws SQLException {
		CDRDto cdr = null;
		try{
			String sqlSentenc = "SELECT * FROM cdrTo WHERE cdrTo.cdrTo_name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				cdr = new CDRDto(rs.getInt("codCDR"),rs.getString("namCDR"),rs.getInt("id_presidentCDR"),rs.getInt("id_college"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cdr;
	}

	public static CDRDto find_by_Id(int id) throws SQLException {
		CDRDto cdr = null;
		try{
			String sqlSentenc = "SELECT * FROM cdrTo WHERE cdrTo.id_cdrTo = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				cdr = new CDRDto(rs.getInt("codCDR"),rs.getString("namCDR"),rs.getInt("id_presidentCDR"),rs.getInt("id_college"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cdr;
	}
}
