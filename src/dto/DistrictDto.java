package dto;

public class DistrictDto {
	
	private int codDis;
	private String namDis;
	private int idMunicipality;
	
	public DistrictDto(int codDis, String namDis, int idMunicipality) {
		super();
		this.codDis = codDis;
		this.namDis = namDis;
		this.idMunicipality = idMunicipality;
	}
	public DistrictDto() {
		super();
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
