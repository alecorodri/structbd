package dto;

import java.util.ArrayList;

public class CDRDto {
	
	private int codCDR;
	private String namCDR;
	private String nam_presidentCDR;
	private ArrayList<VoterDto> VoterDto;
	
	public ArrayList<VoterDto> getVoterDto() {
		return VoterDto;
	}
	public void setVoterDto(ArrayList<VoterDto> voterDto) {
		VoterDto = voterDto;
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
	public String getNam_presidentCDR() {
		return nam_presidentCDR;
	}
	public void setNam_presidentCDR(String nam_presidentCDR) {
		this.nam_presidentCDR = nam_presidentCDR;
	}
	
}
