package dto;

import java.util.ArrayList;

public class Electoral_CollegeDto {
	
	private int codElec; 
	private String namElec;
	private String adrElec;
	private int id_district;
	private ArrayList<CDRDto> CDRDto;
	private ArrayList<AuxVoterDto> AuxVoterDto;
	private ArrayList<PartDto> PartDto;
		
	public ArrayList<AuxVoterDto> getAuxVoterDto() {
		return AuxVoterDto;
	}
	public void setAuxVoterDto(ArrayList<AuxVoterDto> auxVoterDto) {
		AuxVoterDto = auxVoterDto;
	}
	public ArrayList<PartDto> getPartDto() {
		return PartDto;
	}
	public void setPartDto(ArrayList<PartDto> partDto) {
		PartDto = partDto;
	}
	public ArrayList<CDRDto> getCDRDto() {
		return CDRDto;
	}
	public void setCDRDto(ArrayList<CDRDto> cDRDto) {
		CDRDto = cDRDto;
	}
	public void setIdDistrict(int idDistrict) {
		this.id_district = idDistrict;
	}
	public int getIdDistrict() {
		return id_district;
	}
	public int getCodElec() {
		return codElec;
	}
	public void setCodElec(int codElec) {
		this.codElec = codElec;
	}
	public String getNamElec() {
		return namElec;
	}
	public void setNamElec(String namElec) {
		this.namElec = namElec;
	}
	public String getAdrElec() {
		return adrElec;
	}
	public void setAdrElec(String adrElec) {
		this.adrElec = adrElec;
	}
	
}
