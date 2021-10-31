package dto;

public class AuxVoterDto {
	
	private VoterDto VoterDto;
	private boolean vote;
	private String cause;
	private NoVoteCause NoVoteCause;
	
	public NoVoteCause getNoVoteCause() {
		return NoVoteCause;
	}
	public void setNoVoteCause(NoVoteCause noVoteCause) {
		NoVoteCause = noVoteCause;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public VoterDto getVoterDto() {
		return VoterDto;
	}
	public void setVoterDto(VoterDto voterDto) {
		VoterDto = voterDto;
	}
	public boolean isVote() {
		return vote;
	}
	public void setVote(boolean vote) {
		this.vote = vote;
	}
	

}
