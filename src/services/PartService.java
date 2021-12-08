package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import dto.PartDto;

public class PartService {

	public static String createPart(int quan_elect, int quan_delet, int quan_add, int total, int electProces, Timestamp time) throws SQLException{
		String mistake = null;
		try{
<<<<<<< HEAD
			String sqlSentenc = "{call public.create_part(?,?,?,?,?,?)}";
=======
			String sqlSentenc = "{call public.create_college(?,?,?,?,?,?)}";
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1,quan_elect);
			cs.setInt(2,quan_delet);
			cs.setInt(3,quan_add);
			cs.setInt(4,total);
			cs.setInt(5,electProces);
			cs.setTimestamp(6,time);
			cs.execute();
			cs.close();
		}catch(Exception e){
			mistake = e.getMessage();
		}
		return mistake;
	}
	
	public static ArrayList<PartDto> getParts() throws SQLException{
		ArrayList<PartDto> list = new ArrayList<PartDto>();
		try{
<<<<<<< HEAD
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM part");
			while(rs.next()){
				PartDto part = new PartDto(rs.getInt("id_part"), rs.getInt("quan_elect"), rs.getInt("quan_deleted"), rs.getInt("quan_add"), rs.getInt("total"), rs.getInt("process_e"), rs.getTimestamp("date"));
=======
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM college");
			while(rs.next()){
				PartDto part = new PartDto(rs.getTimestamp("date"), rs.getInt("id_part"), rs.getInt("quan_elect"), rs.getInt("quan_deleted"), rs.getInt("quan_add"), rs.getInt("total"), rs.getInt("process_e"));
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
				list.add(part);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
}
