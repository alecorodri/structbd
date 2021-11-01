package dto;

//import java.util.ArrayList;

public class CDRDto {
	
	private int codCDR;
	private String namCDR;
	private int id_presidentCDR;
	private int id_college;
	//private ArrayList<VoterDto> VoterDto;
	
	// public ArrayList<VoterDto> getVoterDto() {
	// 	return VoterDto;
	// }
	// public void setVoterDto(ArrayList<VoterDto> voterDto) {
	// 	VoterDto = voterDto;
	// }

	public void setId_college( int id_college) {
		this.id_college = id_college;
	}

	public int getId_college() {
		return this.id_college;
	}

	public int getCodCDR() {
		return codCDR;
	}
	public void setCodCDR(int codCDR) {
		this.codCDR = codCDR;
	}
	public String getNamCDR() {
		return namCDR;
	}
	public void setNamCDR(String namCDR) {
		this.namCDR = namCDR;
	}
	public int getId_presidentCDR() {
		return id_presidentCDR;
	}
	public void setNam_presidentCDR(int id_presidentCDR) {
		this.id_presidentCDR = id_presidentCDR;
	}
	
}
