<<<<<<< HEAD
package dto;

public class NominatedDto extends VoterDto{
	
	private String ocupNomi;
	private String profeNomi;
	private String phoneNomi;
	private String intgRNomi;
	private String biogDataNomi;
	private int idNominated; 
	private int idVoter; 
	private int processE;
	private int cantVotes;

	public NominatedDto() {
		super();
	}
	public NominatedDto(String ocupNomi, String profeNomi, String phoneNomi,
			String intgRNomi, String biogDataNomi, int idNominated,
			int idVoter, int processE, int cantVotes) {
		super();
		this.ocupNomi = ocupNomi;
		this.profeNomi = profeNomi;
		this.phoneNomi = phoneNomi;
		this.intgRNomi = intgRNomi;
		this.biogDataNomi = biogDataNomi;
		this.idNominated = idNominated;
		this.idVoter = idVoter;
		this.processE = processE;
		this.cantVotes = cantVotes;
	}
	public int getIdNominated() {
		return idNominated;
	}
	public void setIdNominated(int idNominated) {
		this.idNominated = idNominated;
	}

	public int getIdVoter() {
		return idVoter;
	}
	public void setIdVoter(int idVoter) {
		this.idVoter = idVoter;
	}

	public int getProcessE() {
		return processE;
	}
	public void setProcessE(int processE) {
		this.processE = processE;
	}

	public int getCantVotes() {
		return cantVotes;
	}
	public void setCantVotes(int cantVotes) {
		this.cantVotes = cantVotes;
	}

	public String getOcupNomi() {
		return ocupNomi;
	}
	public void setOcupNomi(String ocupNomi) {
		this.ocupNomi = ocupNomi;
	}

	public String getProfeNomi() {
		return profeNomi;
	}
	public void setProfeNomi(String profeNomi) {
		this.profeNomi = profeNomi;
	}

	public String getPhoneNomi() {
		return phoneNomi;
	}
	public void setPhoneNomi(String phoneNomi) {
		this.phoneNomi = phoneNomi;
	}

	public String getIntgRNomi() {
		return intgRNomi;
	}
	public void setIntgRNomi(String intgRNomi) {
		this.intgRNomi = intgRNomi;
	}
	
	public String getBiogDataNomi() {
		return biogDataNomi;
	}
	public void setBiogDataNomi(String biogDataNomi) {
		this.biogDataNomi = biogDataNomi;
	}
	
=======
package dto;

public class NominatedDto extends VoterDto{
	
	private String ocupNomi;
	private String profeNomi;
	private String phoneNomi;
	private String intgRNomi;
	private String biogDataNomi;
	private int idNominated; 
	private int idVoter; 
	private int processE;
	private int cantVotes;

	public NominatedDto() {
		super();
	}
	public NominatedDto(String ocupNomi, String profeNomi, String phoneNomi,
			String intgRNomi, String biogDataNomi, int idNominated,
			int idVoter, int processE, int cantVotes) {
		super();
		this.ocupNomi = ocupNomi;
		this.profeNomi = profeNomi;
		this.phoneNomi = phoneNomi;
		this.intgRNomi = intgRNomi;
		this.biogDataNomi = biogDataNomi;
		this.idNominated = idNominated;
		this.idVoter = idVoter;
		this.processE = processE;
		this.cantVotes = cantVotes;
	}
	public int getIdNominated() {
		return idNominated;
	}
	public void setIdNominated(int idNominated) {
		this.idNominated = idNominated;
	}

	public int getIdVoter() {
		return idVoter;
	}
	public void setIdVoter(int idVoter) {
		this.idVoter = idVoter;
	}

	public int getProcessE() {
		return processE;
	}
	public void setProcessE(int processE) {
		this.processE = processE;
	}

	public int getCantVotes() {
		return cantVotes;
	}
	public void setCantVotes(int cantVotes) {
		this.cantVotes = cantVotes;
	}

	public String getOcupNomi() {
		return ocupNomi;
	}
	public void setOcupNomi(String ocupNomi) {
		this.ocupNomi = ocupNomi;
	}

	public String getProfeNomi() {
		return profeNomi;
	}
	public void setProfeNomi(String profeNomi) {
		this.profeNomi = profeNomi;
	}

	public String getPhoneNomi() {
		return phoneNomi;
	}
	public void setPhoneNomi(String phoneNomi) {
		this.phoneNomi = phoneNomi;
	}

	public String getIntgRNomi() {
		return intgRNomi;
	}
	public void setIntgRNomi(String intgRNomi) {
		this.intgRNomi = intgRNomi;
	}
	
	public String getBiogDataNomi() {
		return biogDataNomi;
	}
	public void setBiogDataNomi(String biogDataNomi) {
		this.biogDataNomi = biogDataNomi;
	}
	
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
}