package dto;

public class MunicipalityDto {
	
	private int codMun;
	private String namMun;
	
	public MunicipalityDto() {
		super();
	}
	public MunicipalityDto(int codMun, String namMun) {
		super();
		this.codMun = codMun;
		this.namMun = namMun;
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
