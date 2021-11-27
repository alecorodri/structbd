package util;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.DistrictDto;
import dto.MunicipalityDto;
import dto.User;
import services.DistrictService;
import services.MunicipalityService;
import services.UserService;

public class LoadTable {

	@SuppressWarnings("serial")
	public static void Load_User(DefaultTableModel date, JTable table, User user) throws SQLException{
		ArrayList<User> uss = UserService.getUsers();
		ArrayList<Integer> id = new ArrayList<>();
		ArrayList<String> name = new ArrayList<String>();

		for(int i=0; i<uss.size(); i++){
			if(uss.get(i).getIdUser()!=user.getIdUser()){
				id.add(uss.get(i).getIdUser());
				name.add(uss.get(i).getName());
			}
		}
		
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Identificadores",id.toArray());
		date.addColumn("Usuarios",name.toArray());
		table.setModel(date);
	}
	
	@SuppressWarnings("serial")
	public static void Load_Municipality(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<MunicipalityDto> mun = MunicipalityService.getMunicipality();
		ArrayList<String> name = new ArrayList<String>();

		for(int i=0; i<mun.size(); i++){
			name.add(mun.get(i).getNamMun());
		}
		
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Municipios",name.toArray());
		table.setModel(date);
	}
	
	@SuppressWarnings("serial")
	public static void Load_District(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<DistrictDto> d = DistrictService.getDistrict();
		ArrayList<String> mun = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();

		for(int i=0; i<d.size(); i++){
			name.add(d.get(i).getNamDis());
			mun.add(MunicipalityService.find_by_Id(d.get(i).getIdMunicipality()).getNamMun());
		}
		
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Circunscripción",name.toArray());
		date.addColumn("Municipio",mun.toArray());
		table.setModel(date);
	}
}
