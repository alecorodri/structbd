package dto;

import java.util.ArrayList;

public class Electoral_ProcessDto {
	
	private int roundNum;
	private int votePerNom; //revisar 
	private int idMunicipality;
	private ArrayList<NominatedDto> NominatedDto;
	private MunicipalityDto MunicipalityDto;
	private ArrayList<VoterDto> delegate;

	public MunicipalityDto getMunicipalityDto() {
		return MunicipalityDto;
	}
	public void setMunicipalityDto(MunicipalityDto municipalityDto) {
		MunicipalityDto = municipalityDto;
	}
	public ArrayList<VoterDto> getDelegate() {
		return delegate;
	}
	public void setDelegate(ArrayList<VoterDto> delegate) {
		this.delegate = delegate;
	}
	public ArrayList<NominatedDto> getNominatedDto() {
		return NominatedDto;
	}
	public void setNominatedDto(ArrayList<NominatedDto> nominatedDto) {
		NominatedDto = nominatedDto;
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
	public int getVotePerNom() {
		return votePerNom;
	}
	public void setVotePerNom(int votePerNom) {
		this.votePerNom = votePerNom;
	}

}