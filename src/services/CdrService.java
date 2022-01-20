<<<<<<< HEAD
package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CDRDto;

public class CdrService {
	
	public static String create_CDR(String namCDR, int id_college) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_cdr(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,namCDR);
			cs.setInt(2,id_college);
			cs.setInt(3, 3);
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
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM cdrdto");
			while(rs.next()){
				int valor;
				if(rs.getObject("id_president")==null){
					valor = 0;
				}else{
					valor = rs.getInt("id_president");
				}
				CDRDto cdr = new CDRDto(rs.getInt("id_cdr"),rs.getString("name_cdr"),valor,rs.getInt("college"));
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
			String sqlSentenc = "SELECT * FROM cdrdto WHERE cdrdto.name_cdr = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				int valor;
				if(rs.getObject("id_president")==null){
					valor = 0;
				}else{
					valor = rs.getInt("id_president");
				}
				cdr = new CDRDto(rs.getInt("id_cdr"),rs.getString("name_cdr"),valor,rs.getInt("college"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cdr;
	}

	public static CDRDto find_by_Id(int id) throws SQLException {
		CDRDto cdr = null;
		try{
			String sqlSentenc = "SELECT * FROM cdrdto WHERE cdrdto.id_cdr = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				cdr = new CDRDto(rs.getInt("id_cdr"),rs.getString("name_cdr"),rs.getInt("id_president"),rs.getInt("college"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cdr;
	}
}
=======
package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CDRDto;

public class CdrService {
	
<<<<<<< HEAD
	public static String create_CDR(String namCDR, int id_college) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_cdr(?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,namCDR);
			cs.setInt(2,id_college);
			cs.setInt(3, 3);
=======
	public static String create_CDR(int codCDR, String namCDR, int id_presidentCDR, int id_college) throws SQLException{
		String mistake = null;
		try{
			String sqlSentenc = "{call public.create_cdr(?,?,?,?)}";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,codCDR);
			cs.setString(2,namCDR);
			cs.setInt(3,id_presidentCDR);
			cs.setInt(4,id_college);
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM cdrdto");
			while(rs.next()){
				int valor;
				if(rs.getObject("id_president")==null){
					valor = 0;
				}else{
					valor = rs.getInt("id_president");
				}
				CDRDto cdr = new CDRDto(rs.getInt("id_cdr"),rs.getString("name_cdr"),valor,rs.getInt("college"));
=======
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM cdrTo");
			while(rs.next()){
				CDRDto cdr = new CDRDto(rs.getInt("codCDR"),rs.getString("namCDR"),rs.getInt("id_presidentCDR"),rs.getInt("id_college"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
			String sqlSentenc = "SELECT * FROM cdrdto WHERE cdrdto.name_cdr = ?";
=======
			String sqlSentenc = "SELECT * FROM cdrTo WHERE cdrTo.cdrTo_name = ?";
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
<<<<<<< HEAD
				int valor;
				if(rs.getObject("id_president")==null){
					valor = 0;
				}else{
					valor = rs.getInt("id_president");
				}
				cdr = new CDRDto(rs.getInt("id_cdr"),rs.getString("name_cdr"),valor,rs.getInt("college"));
=======
				cdr = new CDRDto(rs.getInt("codCDR"),rs.getString("namCDR"),rs.getInt("id_presidentCDR"),rs.getInt("id_college"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cdr;
	}

	public static CDRDto find_by_Id(int id) throws SQLException {
		CDRDto cdr = null;
		try{
<<<<<<< HEAD
			String sqlSentenc = "SELECT * FROM cdrdto WHERE cdrdto.id_cdr = ?";
=======
			String sqlSentenc = "SELECT * FROM cdrTo WHERE cdrTo.id_cdrTo = ?";
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
<<<<<<< HEAD
				cdr = new CDRDto(rs.getInt("id_cdr"),rs.getString("name_cdr"),rs.getInt("id_president"),rs.getInt("college"));
=======
				cdr = new CDRDto(rs.getInt("codCDR"),rs.getString("namCDR"),rs.getInt("id_presidentCDR"),rs.getInt("id_college"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cdr;
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
