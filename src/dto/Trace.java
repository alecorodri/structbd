package dto;

import java.util.Date;

public class Trace {

    private int idTrace;
    private int user;
    private String operation;
    private Date date;
    private int ip;

    public Trace() {
		super();
	}
	public Trace(int idTrace, int user, String operation, Date date, int ip) {
		super();
		this.idTrace = idTrace;
		this.user = user;
		this.operation = operation;
		this.date = date;
		this.ip = ip;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public int getIdTrace() {
        return this.idTrace;
    }
    public void setIdTrace(int idTrace) {
        this.idTrace = idTrace;
    }
    public int getUser() {
        return this.user;
    }
    public void setUser(int user) {
        this.user = user;
    }
    public String getOperation() {
        return this.operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}