package dto;

public class Electoral_ProcessDto {
	
	private int roundNum;
	private int id_EProcess; 
	private int idMunicipality;

	public Electoral_ProcessDto() {
		super();
	}
	public Electoral_ProcessDto(int roundNum, int id_EProcess,
			int idMunicipality) {
		super();
		this.roundNum = roundNum;
		this.id_EProcess = id_EProcess;
		this.idMunicipality = idMunicipality;
	}
	public int getId_EProcess() {
		return id_EProcess;
	}
	public void setId_EProcess(int id_EProcess) {
		this.id_EProcess = id_EProcess;
	}
	public void setIdMunicipality(int idMunicipality) {
		this.idMunicipality = idMunicipality;
	}
	public int getIdMunicipality() {
		return idMunicipality;
	}
	public int getRoundNum() {
		return roundNum;
	}
	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}
}