<<<<<<< HEAD
package dto;

import java.sql.Timestamp;

public class VoterDto {
	
	private int numID;
	private String namVot;
	private String adressVot;
	private Timestamp birthdayVot;
	private int cdr;
	private int vote;
	private String cause;

	public VoterDto() {
		super();
	}
	public VoterDto(int numID, String namVot, Timestamp birthdayVot, String adressVot, int cdr, int vote, String cause) {
		super();
		this.numID = numID;
		this.namVot = namVot;
		this.adressVot = adressVot;
		this.birthdayVot = birthdayVot;
		this.cdr = cdr;
		this.vote = vote;
		this.cause = cause;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getCdr() {
		return cdr;
	}
	public void setCdr(int cdr) {
		this.cdr = cdr;
	}
	public int getNumID() {
		return numID;
	}
	public void setNumID(int numID) {
		this.numID = numID;
	}
	public String getNamVot() {
		return namVot;
	}
	public void setNamVot(String namVot) {
		this.namVot = namVot;
	}
	public String getAdrVot() {
		return adressVot;
	}
	public void setAdrVot(String adrVot) {
		this.adressVot = adrVot;
	}
	public Timestamp getDateVot() {
		return birthdayVot;
	}
	public void setDateVot(Timestamp dateVot) {
		this.birthdayVot = dateVot;
	}
}
=======
package dto;

import java.sql.Timestamp;

public class VoterDto {
	
	private int numID;
	private String namVot;
	private String adressVot;
<<<<<<< HEAD
	private Timestamp birthdayVot;
=======
	private Date birthdayVot;
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
	private int cdr;
	private int vote;
	private String cause;

	public VoterDto() {
		super();
	}
<<<<<<< HEAD
	public VoterDto(int numID, String namVot, Timestamp birthdayVot, String adressVot, int cdr, int vote, String cause) {
=======
	public VoterDto(int numID, String namVot, String adressVot,
			Date birthdayVot, int cdr, boolean vote, String cause) {
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		super();
		this.numID = numID;
		this.namVot = namVot;
		this.adressVot = adressVot;
		this.birthdayVot = birthdayVot;
		this.cdr = cdr;
		this.vote = vote;
		this.cause = cause;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getCdr() {
		return cdr;
	}
	public void setCdr(int cdr) {
		this.cdr = cdr;
	}
	public int getNumID() {
		return numID;
	}
	public void setNumID(int numID) {
		this.numID = numID;
	}
	public String getNamVot() {
		return namVot;
	}
	public void setNamVot(String namVot) {
		this.namVot = namVot;
	}
	public String getAdrVot() {
		return adressVot;
	}
	public void setAdrVot(String adrVot) {
		this.adressVot = adrVot;
	}
<<<<<<< HEAD
	public Timestamp getDateVot() {
		return birthdayVot;
	}
	public void setDateVot(Timestamp dateVot) {
=======
	public Date getDateVot() {
		return birthdayVot;
	}
	public void setDateVot(Date dateVot) {
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		this.birthdayVot = dateVot;
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
