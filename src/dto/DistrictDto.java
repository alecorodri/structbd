package dto;

import java.util.ArrayList;

public class DistrictDto {
	
	private int codDis;
	private String namDis;
	private int idMunicipality;
	private ArrayList<Electoral_CollegeDto> Electoral_CollegeDto;
	
	public ArrayList<Electoral_CollegeDto> getElectoral_CollegeDto() {
		return Electoral_CollegeDto;
	}
	public void setElectoral_CollegeDto(
			ArrayList<Electoral_CollegeDto> electoral_CollegeDto) {
		Electoral_CollegeDto = electoral_CollegeDto;
	}	
	public void setIdMunicipality(int idMunicipality) {
		this.idMunicipality = idMunicipality;
	}
	public int getIdMunicipality() {
		return idMunicipality;
	}
	public int getCodDis() {
		return codDis;
	}
	public void setCodDis(int codDis) {
		this.codDis = codDis;
	}
	public String getNamDis() {
		return namDis;
	}
	public void setNamDis(String namDis) {
		this.namDis = namDis;
	}
	
}
