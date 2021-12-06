package util;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.CDRDto;
import dto.DistrictDto;
import dto.Electoral_CollegeDto;
import dto.Electoral_ProcessDto;
import dto.MunicipalityDto;
import dto.NominatedDto;
import dto.PartDto;
import dto.User;
import dto.VoterDto;
import services.CdrService;
import services.CollegeService;
import services.DistrictService;
import services.ElectoralProcessService;
import services.MunicipalityService;
import services.NominatedService;
import services.PartService;
import services.UserService;
import services.VoterService;

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
		ArrayList<Integer> cantD = new ArrayList<>();
		ArrayList<Integer> cantC = new ArrayList<>();
		ArrayList<Integer> cantCdr = new ArrayList<>();
		ArrayList<Integer> cantElect = new ArrayList<>();

		int cantdist = 0;
		int cantcollege = 0;
		int cantcdr = 0;
		int cantel = 0;

		ArrayList<DistrictDto> distritos = DistrictService.getDistrict();
		ArrayList<Electoral_CollegeDto> colegios = CollegeService.getVoters();
		ArrayList<CDRDto> cdrs = CdrService.getCDRDto();
		ArrayList<VoterDto> electores = VoterService.getVoters();

		for(int i=0; i<mun.size(); i++){
			name.add(mun.get(i).getNamMun());
			for (int j = 0; j < distritos.size(); j++) {
				if(distritos.get(j).getIdMunicipality()==mun.get(i).getCodMun()){
					cantdist++;
					for (int j2 = 0; j2 < colegios.size(); j2++) {
						if(colegios.get(j2).getId_district()==distritos.get(j).getCodDis()){
							cantcollege++;
							for (int k = 0; k < cdrs.size(); k++) {
								if(cdrs.get(k).getId_college()==colegios.get(j2).getCodCollege()){
									cantcdr++;
									for (int k2 = 0; k2 < electores.size(); k2++) {
										if(electores.get(k2).getCdr()==cdrs.get(k).getCodCDR()){
											cantel++;
										}
									}
								}
							}
						}
					}
				}
			}
			cantD.add(cantdist);
			cantC.add(cantcollege);
			cantCdr.add(cantcdr);
			cantElect.add(cantel);
			cantdist = 0;
			cantcollege = 0;
			cantcdr = 0;
			cantel = 0;
		}

		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Municipios",name.toArray());
		date.addColumn("Cant Distritos",cantD.toArray());
		date.addColumn("Cant Colegios",cantC.toArray());
		date.addColumn("Cant CDR",cantCdr.toArray());
		date.addColumn("Cant Electores",cantElect.toArray());
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

	@SuppressWarnings({ "serial", "deprecation" })
	public static void Load_Elector(DefaultTableModel date, JTable table, String collegio) throws SQLException{
		ArrayList<VoterDto> voters = VoterService.getVoters();
		ArrayList<CDRDto> c = CdrService.getCDRDto();
		ArrayList<CDRDto> cdr =  new ArrayList<>();
		ArrayList<Integer> id = new ArrayList<Integer>();
		Electoral_CollegeDto col = CollegeService.find_by_Name(collegio);
		ArrayList<String> cumple = new ArrayList<>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> direc = new ArrayList<>();
		ArrayList<String> vote = new ArrayList<>();
		for (int j = 0; j < c.size(); j++) {
			if (c.get(j).getId_college() == col.getCodCollege()) {
				cdr.add(c.get(j));
			}
		}
		for(int i=0; i<voters.size(); i++){
			for (int k = 0; k < cdr.size(); k++) {
				if (voters.get(i).getCdr() == cdr.get(k).getCodCDR()) {
					name.add(voters.get(i).getNamVot());
					cumple.add(voters.get(i).getDateVot().toLocaleString());
					direc.add(voters.get(i).getAdrVot());
					if(voters.get(i).getVote() == 1){
						vote.add("X");
					}else if(voters.get(i).getVote() == 2){
						vote.add("-");
					}else{
						vote.add("Aún no");
					}
				}
			}
		}
		for (int e = 0; e < name.size(); e++) {
			id.add(e+1);
		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		date.addColumn("",id.toArray());
		date.addColumn("Electores",name.toArray());
		date.addColumn("Fecha Nac.",cumple.toArray());
		date.addColumn("Dirección",direc.toArray());
		date.addColumn("¿Votó?",vote.toArray());
		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_Nominated(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<NominatedDto> nom = NominatedService.getNominatedDto();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> cell = new ArrayList<String>();
		ArrayList<String> adrs = new ArrayList<String>();
		ArrayList<Integer> cantVotos = new ArrayList<Integer>();
		for(int i=0; i<nom.size(); i++){
			VoterDto vot = VoterService.find_by_Id(nom.get(i).getIdVoter());
			name.add(vot.getNamVot());
			adrs.add(vot.getAdrVot());
			cell.add(nom.get(i).getPhoneNomi());
			cantVotos.add(nom.get(i).getCantVotes());
		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Nombres",name.toArray());
		date.addColumn("Telefonos",cell.toArray());
		date.addColumn("Dirección",adrs.toArray());
		date.addColumn("Cantidad de votos",cantVotos.toArray());

		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_CDR(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<CDRDto> c = CdrService.getCDRDto();
		ArrayList<String> nameCDR = new ArrayList<String>();
		ArrayList<String> nameColleg = new ArrayList<String>();
		ArrayList<String> nameDist = new ArrayList<String>();
		ArrayList<String> nameMun = new ArrayList<String>();

		for(int i=0; i<c.size(); i++){
			nameCDR.add(c.get(i).getNamCDR());
			nameColleg.add(CollegeService.find_by_Id(c.get(i).getId_college()).getNameCollege());
			nameDist.add(DistrictService.find_by_Id(CollegeService.find_by_Id(c.get(i).getId_college()).getId_district()).getNamDis());
			nameMun.add(MunicipalityService.find_by_Id(DistrictService.find_by_Id(CollegeService.find_by_Id(c.get(i).getId_college()).getId_district()).getIdMunicipality()).getNamMun());
		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		date.addColumn("CDR",nameCDR.toArray());
		date.addColumn("Colegio E",nameColleg.toArray());
		date.addColumn("Circunscripción",nameDist.toArray());
		date.addColumn("Municipio",nameMun.toArray());
		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_College(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<Electoral_CollegeDto> d = CollegeService.getVoters();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> dir = new ArrayList<String>();

		for(int i=0; i<d.size(); i++){
			name.add(d.get(i).getNameCollege());
			dir.add(d.get(i).getAdress());
		}

		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Colegio Electoral",name.toArray());
		date.addColumn("Dirección",dir.toArray());
		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_ElectoralProcess(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<Electoral_ProcessDto> e = ElectoralProcessService.getElectoral_ProcessDto();
		ArrayList<Integer> name = new ArrayList<Integer>();
		ArrayList<Integer> rounds = new ArrayList<Integer>();
		ArrayList<String> muncp = new ArrayList<String>();

		for(int i=0; i<e.size(); i++){
			MunicipalityDto mun = MunicipalityService.find_by_Id(e.get(i).getIdMunicipality());
			muncp.add(mun.getNamMun());
			name.add(e.get(i).getId_EProcess());
			rounds.add(e.get(i).getRoundNum());
		}

		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Proceso Electoral",name.toArray());
		date.addColumn("Municipio",muncp.toArray());
		date.addColumn("Vueltas",rounds.toArray());

		table.setModel(date);
	}

	@SuppressWarnings({ "serial", "deprecation" })
	public static void Load_Elector_1(DefaultTableModel date, JTable table, String cdr) throws SQLException{
		ArrayList<VoterDto> voters = VoterService.getVoters();
		ArrayList<String> cumple = new ArrayList<>();
		ArrayList<String> name = new ArrayList<String>();
		CDRDto cdrr = CdrService.find_by_Name(cdr);
		for(int i=0; i<voters.size(); i++){
			if(voters.get(i).getVote() == 0 && voters.get(i).getCdr()==cdrr.getCodCDR()){
				name.add(voters.get(i).getNamVot());
				cumple.add(voters.get(i).getDateVot().toLocaleString());
			}

		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		date.addColumn("Electores",name.toArray());
		date.addColumn("Nacimiento",cumple.toArray());
		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_Nominated_1(DefaultTableModel date, JTable table, String cdr) throws SQLException{
		ArrayList<NominatedDto> nom = NominatedService.getNominatedDto();
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<String> name = new ArrayList<String>();	
		DistrictDto circ = DistrictService.find_by_Id(CollegeService.find_by_Id(CdrService.find_by_Name(cdr).getId_college()).getId_district());
		for(int i=0; i<nom.size(); i++){
			VoterDto vot = VoterService.find_by_Id(nom.get(i).getIdVoter());
			if ((CollegeService.find_by_Id(CdrService.find_by_Id(vot.getCdr()).getId_college())).getId_district()==circ.getCodDis()) {
				name.add(vot.getNamVot());
				id.add(nom.get(i).getIdNominated());
			}
		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		date.addColumn("ID",id.toArray());
		date.addColumn("Nombres",name.toArray());
		table.setModel(date);
	}

	@SuppressWarnings({ "deprecation", "serial" })
	public static void Load_Part(DefaultTableModel date, JTable table, String municipio) throws SQLException{
		ArrayList<PartDto> part = PartService.getParts();
		ArrayList<String> fecha = new ArrayList<>();
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<Integer> quanElect = new ArrayList<Integer>();
		ArrayList<Integer> quanDeleted = new ArrayList<Integer>();
		ArrayList<Integer> quanAd = new ArrayList<Integer>();
		ArrayList<Integer> total = new ArrayList<Integer>();
		Electoral_ProcessDto ee = ElectoralProcessService.find_by_Municipality(MunicipalityService.find_by_Name(municipio).getCodMun());
		for(int i=0; i<part.size(); i++){
			if(part.get(i).getProcessE()==ee.getId_EProcess()){
				id.add(part.get(i).getPartID());
				fecha.add(part.get(i).getDate().toLocaleString());
				quanElect.add(part.get(i).getQuanElect());
				quanAd.add(part.get(i).getQuanAd());
				quanDeleted.add(part.get(i).getQuanDeleted());
				total.add(part.get(i).getTotal());
			}
		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		date.addColumn("ID Parte",id.toArray());
		date.addColumn("Hora y Fecha",fecha.toArray());
		date.addColumn("Cantidad votados",quanElect.toArray());
		date.addColumn("Cantidad agregados",quanAd.toArray());
		date.addColumn("Cantidad eliminados",quanDeleted.toArray());
		date.addColumn("Total",total.toArray());
		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_NoVoters(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<VoterDto> voters = VoterService.getVoters();
		ArrayList<String> cause = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> cdrs = new ArrayList<String>();
		CDRDto cdr = null;
		for(int i=0; i<voters.size(); i++){
			if(voters.get(i).getVote() == 2){				
				cause.add(voters.get(i).getCause());
				name.add(voters.get(i).getNamVot());
				cdr = CdrService.find_by_Id(voters.get(i).getCdr());
				cdrs.add(cdr.getNamCDR());
			}
		}	
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		date.addColumn("Nombre",name.toArray());
		date.addColumn("Causa",cause.toArray());
		date.addColumn("CDR",cdrs.toArray());	
		table.setModel(date);
	}

	@SuppressWarnings("serial")
	public static void Load_RepeatDistrict(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<DistrictDto> districts = DistrictService.getDistrict();
		ArrayList<String> munc = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Integer> rounds = new ArrayList<Integer>();
		ArrayList<Electoral_ProcessDto> e = ElectoralProcessService.getElectoral_ProcessDto();
		for(int i=0; i<e.size(); i++){

			if(e.get(i).getRoundNum() >= 2)
				for(int j=0; j<districts.size(); j++){
					if(districts.get(j).getIdMunicipality() == e.get(i).getIdMunicipality()){
						name.add(districts.get(j).getNamDis());
						MunicipalityDto mun = MunicipalityService.find_by_Id(districts.get(j).getIdMunicipality());
						munc.add(mun.getNamMun());
						rounds.add(e.get(i).getRoundNum());

					}
				}
		}
		date = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Circunscripción",name.toArray());
		date.addColumn("Municipio",munc.toArray());
		date.addColumn("Rondas",rounds.toArray());

		table.setModel(date);
	}
}
