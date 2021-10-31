package dto;

import java.util.ArrayList;

public class MunicipalityDto {
	
	private int codMun;
	private String namMun;
	private ArrayList<DistrictDto> DistrictDto;
	
	public ArrayList<DistrictDto> getDistrictDto() {
		return DistrictDto;
	}
	public void setDistrictDto(ArrayList<DistrictDto> districtDto) {
		DistrictDto = districtDto;
	}
	public int getCodMun() {
		return codMun;
	}
	public void setCodMun(int codMun) {
		this.codMun = codMun;
	}
	public String getNamMun() {
		return namMun;
	}
	public void setNamMun(String namMun) {
		this.namMun = namMun;
	}
	
	

}
