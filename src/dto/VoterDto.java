package dto;

import java.sql.Date;

public class VoterDto {
	
	private int numID;
	private String namVot;
	private String adrVot;
	private Date dateVot;
	
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
