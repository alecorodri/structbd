package dto;

import java.sql.Date;

public class VoterDto {
	
	private int numID;
	private String namVot;
	private String adrVot;
	private Date dateVot;
	private int cdr;
	private boolean vote;
	private String cause;


	
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public boolean isVote() {
		return vote;
	}
	public void setVote(boolean vote) {
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
		return adrVot;
	}
	public void setAdrVot(String adrVot) {
		this.adrVot = adrVot;
	}
	public Date getDateVot() {
		return dateVot;
	}
	public void setDateVot(Date dateVot) {
		this.dateVot = dateVot;
	}
	

}
