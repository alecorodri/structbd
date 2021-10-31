package dto;

import java.sql.Date;

public class PartDto {

	private Date date;   //dentro de esa variable va fecha y hora	
	private int partID;
	private int quanElect;
	private int quanDeleted;
	private int quanAd;
	private int total;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPartID() {
		return partID;
	}
	public void setPartID(int partID) {
		this.partID = partID;
	}
	public int getQuanElect() {
		return quanElect;
	}
	public void setQuanElect(int quanElect) {
		this.quanElect = quanElect;
	}
	public int getQuanDeleted() {
		return quanDeleted;
	}
	public void setQuanDeleted(int quanDeleted) {
		this.quanDeleted = quanDeleted;
	}
	public int getQuanAd() {
		return quanAd;
	}
	public void setQuanAd(int quanAd) {
		this.quanAd = quanAd;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
